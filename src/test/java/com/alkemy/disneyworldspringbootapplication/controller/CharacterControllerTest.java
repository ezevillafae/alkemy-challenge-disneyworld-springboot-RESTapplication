package com.alkemy.disneyworldspringbootapplication.controller;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.CharacterFilterDto;
import com.alkemy.disneyworldspringbootapplication.exception.CharacterNotFound;
import com.alkemy.disneyworldspringbootapplication.service.CharacterService;
import com.alkemy.disneyworldspringbootapplication.shared.CharacterDtoMother;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CharacterService service;


    @Test
    @WithMockUser
    public void when_post_valid_input_then_return_character() throws Exception {
        CharacterDto dto = CharacterDtoMother.random();
        when(service.save(dto)).thenReturn(dto);

        mvc.perform(post("/characters").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void when_post_invalid_input_then_return_400badRequest() throws Exception {
        CharacterDto dto = CharacterDtoMother.invalidRandom();

        mvc.perform(post("/characters").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void when_update_with_valid_input_then_return_character() throws Exception {
        CharacterDto dto = CharacterDtoMother.random();
        when(service.update(dto)).thenReturn(Optional.of(dto));

        String expectedStringBody = objectMapper.writeValueAsString(dto);

        MvcResult result = mvc.perform(put("/characters").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andReturn();

        String actualStringBody = result.getResponse().getContentAsString();

        assertThat(actualStringBody).isEqualTo(expectedStringBody);
    }

    @Test
    @WithMockUser
    public void when_update_with_invalid_input_then_return_400badRequest() throws Exception {
        CharacterDto dto = CharacterDtoMother.invalidRandom();

        mvc.perform(put("/characters").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void when_update_invalidCharacterId_then_CharacterNotFoundException() throws Exception {
        CharacterDto dto = CharacterDtoMother.random();
        when(service.update(dto)).thenReturn(Optional.empty());

        mvc.perform(put("/characters").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest()).andExpect(result -> assertTrue(result.getResolvedException() instanceof CharacterNotFound));

    }

    @Test
    @WithMockUser
    public void when_get_without_filter_then_return_all_characters() throws Exception {
        List<CharacterDto> characterDtos = CharacterDtoMother.randoms();
        CharacterFilterDto filterDto = new CharacterFilterDto();
        when(service.findAllByFilter(filterDto)).thenReturn(characterDtos);

        mvc.perform(get("/characters").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void when_get_with_valid_id_then_return_character() throws Exception {
        CharacterDto dto = CharacterDtoMother.random();
        String expectedStringBody = objectMapper.writeValueAsString(dto);
        when(service.findById(anyLong())).thenReturn(Optional.of(dto));

        MvcResult actualResult = mvc.perform(get("/characters/{id}", anyLong()).with(csrf())).andReturn();
        String actualStringBody = actualResult.getResponse().getContentAsString();

        assertThat(actualStringBody).isEqualToIgnoringWhitespace(expectedStringBody);
    }

    @Test
    @WithMockUser
    public void when_delete_character_then_return_200_status() throws Exception {

       mvc.perform(delete("/characters/{id}", anyLong()).with(csrf())).andExpect(status().isOk());
    }




}
