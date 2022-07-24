package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterBasicDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;

import java.util.ArrayList;
import java.util.List;

public class FilmDtoMother {
    public static FilmDto random() {
        FilmDto dto = new FilmDto();
        dto.setId(10L);
        dto.setTitle("Random title");
        dto.setRating(DoubleMother.random());
        dto.setCharacters(characterBasicDtoList());
        dto.setImage("img/random-image.jpg");
        dto.setCreationDate(LocalDateMother.random());
        return dto;
    }

    public static List<FilmDto> randoms() {
        List<FilmDto> dtoList = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            dtoList.add(random());
        }
        return dtoList;
    }

    public static FilmDto invalidRandom() {
        FilmDto dto = new FilmDto();
        dto.setId(10L);
        dto.setTitle("");
        dto.setRating(-4d);
        dto.setCharacters(characterBasicDtoList());
        dto.setImage("");
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
