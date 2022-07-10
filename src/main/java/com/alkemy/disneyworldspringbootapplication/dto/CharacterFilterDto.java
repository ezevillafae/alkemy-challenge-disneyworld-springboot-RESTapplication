package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CharacterFilterDto {
    private String id;
    private String name;
    private String age;
    private String weight;
    private Set<Long> movies;
}
