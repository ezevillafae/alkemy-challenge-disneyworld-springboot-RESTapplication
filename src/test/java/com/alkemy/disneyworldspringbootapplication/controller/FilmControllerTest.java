package com.alkemy.disneyworldspringbootapplication.controller;

import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmFilterDto;
import com.alkemy.disneyworldspringbootapplication.exception.FilmNotFound;
import com.alkemy.disneyworldspringbootapplication.exception.ParamNotFound;
import com.alkemy.disneyworldspringbootapplication.service.FilmService;
import com.alkemy.disneyworldspringbootapplication.shared.FilmDtoMother;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilmService service;

    @Test
    @WithMockUser
    public void when_post_with_valid_input_then_return_201created() throws Exception {
        FilmDto dto = FilmDtoMother.random();
        when(service.save(dto)).thenReturn(dto);

        mvc.perform(post("/movies").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated());

    }

    @Test
    @WithMockUser
    public void when_post_with_invalid_input_then_return_400BadRequest() throws Exception{
        FilmDto dto = FilmDtoMother.invalidRandom();
        when(service.save(dto)).thenReturn(dto);

        mvc.perform(post("/movies").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void when_add_character_with_valid_input_then_return_200ok() throws Exception{
        FilmDto dto = FilmDtoMother.random();
        when(service.addCharacterIntoFilm(anyLong(), anyLong())).thenReturn(Optional.of(dto));

        mvc.perform(
                post("/movies/{idMovie}/characters/{idCharacter}", 1L, 2L).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void when_add_character_with_invalid_input_then_return_400badRequest() throws Exception{
        FilmDto dto = FilmDtoMother.random();
        when(service.addCharacterIntoFilm(anyLong(), anyLong())).thenReturn(Optional.empty());

        mvc.perform(
                post("/movies/{idMovie}/characters/{idCharacter}", 1L, 2L).with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void when_update_with_valid_input_then_return_200ok() throws Exception {
        FilmDto dto = FilmDtoMother.random();
        when(service.update(dto)).thenReturn(Optional.of(dto));

        mvc.perform(
                put("/movies").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void when_update_with_invalid_input_then_return_400badRequest() throws Exception {
        FilmDto dto = FilmDtoMother.invalidRandom();
        when(service.update(dto)).thenReturn(Optional.of(dto));

        mvc.perform(
                        put("/movies").with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void when_update_with_filmNotFound_then_filmNotFoundException() throws Exception {
        FilmDto dto = FilmDtoMother.random();
        when(service.update(dto)).thenReturn(Optional.empty());

        mvc.perform(
                        put("/movies").with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest()).andExpect(result -> assertTrue(result.getResolvedException() instanceof FilmNotFound));
    }

    @Test
    @WithMockUser
    public void when_get_by_valid_id_then_return_200ok() throws Exception {
        FilmDto dto = FilmDtoMother.random();
        when(service.findById(anyLong())).thenReturn(Optional.of(dto));

        mvc.perform(
                get("/movies/{id}", 1L)
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void when_get_by_invalid_id_then_FilmNotFoundException() throws Exception {
        when(service.findById(anyLong())).thenReturn(Optional.empty());

        mvc.perform(
                get("/movies/{id}", 1L)
        ).andExpect(status().isBadRequest()).andExpect(result -> assertTrue(result.getResolvedException() instanceof FilmNotFound));
    }

    @Test
    @WithMockUser
    public void when_get_all_films_without_filters_then_return_200ok() throws Exception {
        List<FilmDto> filmDtoList = FilmDtoMother.randoms();
        FilmFilterDto filterDto = new FilmFilterDto();

        when(service.findAllByFilter(filterDto)).thenReturn(filmDtoList);

        mvc.perform(get("/movies")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void when_remove_character_with_valid_input_then_return_200ok() throws Exception{
        FilmDto dto = FilmDtoMother.random();
        when(service.removeCharacterIntoFilm(anyLong(),anyLong())).thenReturn(Optional.of(dto));

        mvc.perform(
                        delete("/movies/{idMovie}/characters/{idCharacter}", 1L, 2L).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void when_remove_character_with_invalid_input_then_ParamNotFound() throws Exception{
        FilmDto dto = FilmDtoMother.random();
        when(service.removeCharacterIntoFilm(anyLong(),anyLong())).thenReturn(Optional.empty());
        mvc.perform(
                        delete("/movies/{idMovie}/characters/{idCharacter}", 1L, 2L).with(csrf()))
                .andExpect(status().isBadRequest()).andExpect(result -> assertTrue(result.getResolvedException() instanceof ParamNotFound));
    }

}
