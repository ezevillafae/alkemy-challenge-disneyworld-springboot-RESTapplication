package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterBasicDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;

import java.util.ArrayList;
import java.util.List;

public class FilmDtoMother {
    public static FilmDto random() {
        FilmDto dto = new FilmDto();
        dto.setId(10l);
        dto.setTitle("Random title");
        dto.setRating(DoubleMother.random());
        dto.setCharacters(characterBasicDtoList());
        dto.setImage("img/random-image.jpg");
        dto.setCreationDate(LocalDateMother.random());
        return dto;
    }

    public static List<CharacterBasicDto> characterBasicDtoList() {
        List<CharacterBasicDto> basicDtos = new ArrayList<>();
        CharacterBasicDto characterBasicDto = new CharacterBasicDto();
        characterBasicDto.setAge(IntegerMother.random());
        characterBasicDto.setHistory("History random");
        characterBasicDto.setImage("img/random-image.jpg");
        characterBasicDto.setName("Will Smith");
        characterBasicDto.setWeight(DoubleMother.random());

        basicDtos.add(characterBasicDto);

        return basicDtos;
    }
}
