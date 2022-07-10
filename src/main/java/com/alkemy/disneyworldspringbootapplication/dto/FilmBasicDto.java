package com.alkemy.disneyworldspringbootapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilmBasicDto {
    private Long id;
    private String image;
    private String title;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    private Double rating;
    private List<GenreBasicDto> genres;
}
