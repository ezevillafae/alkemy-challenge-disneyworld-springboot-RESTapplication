package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.dto.GenreDto;

public class GenreDtoMother {
    public static GenreDto random() {
        GenreDto dto = new GenreDto();
        dto.setImage("img/random-genre-image.jpg");
        dto.setName("Animation");
        return dto;
    }
    public static GenreDto invalidRandom() {
        GenreDto dto = new GenreDto();
        dto.setImage("");
        dto.setName("");
        return dto;
    }
}
