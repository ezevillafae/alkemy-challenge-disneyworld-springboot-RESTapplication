package com.alkemy.disneyworldspringbootapplication.controller;

import com.alkemy.disneyworldspringbootapplication.dto.GenreDto;
import com.alkemy.disneyworldspringbootapplication.service.GenreService;
import com.alkemy.disneyworldspringbootapplication.shared.GenreDtoMother;
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

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenreService service;

    @Test
    @WithMockUser
    public void when_save_with_valid_input_then_return_200ok() throws Exception {
        GenreDto dto = GenreDtoMother.random();
        when(service.save(dto)).thenReturn(dto);

        mvc.perform(post("/genres").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void when_save_with_invalid_input_then_return_400badRequest() throws Exception {
        GenreDto dto = GenreDtoMother.invalidRandom();
        when(service.save(dto)).thenReturn(dto);

        mvc.perform(post("/genres").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
