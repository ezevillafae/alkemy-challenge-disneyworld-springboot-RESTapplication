package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenreDto {
    private Long id;
    private String name;
    private String image;
    private List<FilmBasicDto> films;
}
