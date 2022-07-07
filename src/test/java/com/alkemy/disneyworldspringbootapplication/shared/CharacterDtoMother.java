package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmBasicDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;

import java.util.ArrayList;
import java.util.List;

public class CharacterDtoMother {
    public static CharacterDto random() {
        CharacterDto dto = new CharacterDto();
        dto.setAge(IntegerMother.random());
        dto.setImage("img/random-image.jpg");
        dto.setName("Will Smith");
        dto.setHistory("random history");
        dto.setFilms(filmBasicDtoList());
        dto.setId(10l);
        dto.setWeight(DoubleMother.random());

        return dto;
    }

    private static List<FilmBasicDto> filmBasicDtoList() {
        List<FilmBasicDto> basicDtos = new ArrayList<>();
        FilmBasicDto basicDto = new FilmBasicDto();
        basicDto.setCreationDate(LocalDateMother.random());
        basicDto.setId(10l);
        basicDto.setImage("img/random-image.jpg");
        basicDto.setRating(DoubleMother.random());
        basicDto.setTitle("Random title");
        basicDtos.add(basicDto);

        return basicDtos;
    }
}
