package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.Data;

@Data
public class CharacterBasicDto {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
}
