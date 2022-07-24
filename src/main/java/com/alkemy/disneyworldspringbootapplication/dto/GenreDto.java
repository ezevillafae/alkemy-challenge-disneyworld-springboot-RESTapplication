package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GenreDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    private List<FilmBasicDto> films;
}
