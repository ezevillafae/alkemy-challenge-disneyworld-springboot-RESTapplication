package com.alkemy.disneyworldspringbootapplication.dto;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CharacterDto {
    private Long id;
    @NotBlank
    private String image;
    @NotBlank
    private String name;
    @Min(0)
    private Integer age;
    @Min(0)
    private Double weight;
    @NotBlank
    private String history;
    private List<FilmBasicDto> films;
}
