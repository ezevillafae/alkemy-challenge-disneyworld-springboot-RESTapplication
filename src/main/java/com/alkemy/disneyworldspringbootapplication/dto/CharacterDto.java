package com.alkemy.disneyworldspringbootapplication.dto;


import lombok.Data;

import java.util.List;

@Data
public class CharacterDto {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<FilmBasicDto> films;
}
