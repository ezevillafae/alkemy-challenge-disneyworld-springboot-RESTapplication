package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmBasicDto;

import java.util.ArrayList;
import java.util.List;

public class CharacterDtoMother {
    public static CharacterDto random() {
        CharacterDto dto = new CharacterDto();
        dto.setAge(IntegerMother.random());
        dto.setImage("img/random-image.jpg");
        dto.setName("Will Smith");
        dto.setHistory("random history");
        dto.setWeight(DoubleMother.random());

        return dto;
    }

    public static CharacterDto invalidRandom() {
        CharacterDto dto = new CharacterDto();
        dto.setAge(-10);
        dto.setImage("img/random-image.jpg");
        dto.setName("");
        dto.setHistory("");
        dto.setWeight(-10d);

        return dto;
    }

    public static List<CharacterDto> randoms() {
        List<CharacterDto> characterDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            characterDtos.add(random());
        }
        return characterDtos;
    }

    private static List<FilmBasicDto> filmBasicDtoList() {
        List<FilmBasicDto> basicDtos = new ArrayList<>();
        FilmBasicDto basicDto = new FilmBasicDto();
        basicDto.setCreationDate(LocalDateMother.random());
        basicDto.setId(10L);
        basicDto.setImage("img/random-image.jpg");
        basicDto.setRating(DoubleMother.random());
        basicDto.setTitle("Random title");
        basicDtos.add(basicDto);

        return basicDtos;
    }
}
