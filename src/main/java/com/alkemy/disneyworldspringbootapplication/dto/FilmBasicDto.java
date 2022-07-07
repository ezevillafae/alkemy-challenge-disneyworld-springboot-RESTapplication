package com.alkemy.disneyworldspringbootapplication.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilmBasicDto {
    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;
    private Double rating;
}
