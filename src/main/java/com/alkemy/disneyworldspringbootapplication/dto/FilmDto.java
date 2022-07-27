package com.alkemy.disneyworldspringbootapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {
    private Long id;

    @NotBlank
    private String image;
    @NotBlank
    private String title;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate creationDate;
    @Min(0) @Max(5)
    private Double rating;

    private List<GenreBasicDto> genres;
    private List<CharacterBasicDto> characters;

    public void addCharacter(CharacterBasicDto characterBasicDto) {
        characters.add(characterBasicDto);
    }

    public void removeCharacter(CharacterBasicDto characterBasicDto) {
        characters.remove(characterBasicDto);
    }


}
