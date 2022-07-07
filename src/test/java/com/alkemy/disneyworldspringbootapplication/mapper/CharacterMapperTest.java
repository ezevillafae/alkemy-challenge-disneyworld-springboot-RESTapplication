package com.alkemy.disneyworldspringbootapplication.mapper;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import com.alkemy.disneyworldspringbootapplication.shared.CharacterDtoMother;
import com.alkemy.disneyworldspringbootapplication.shared.CharacterEntityMother;
import com.alkemy.disneyworldspringbootapplication.shared.FilmEntityMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        CharacterMapperImpl.class
})
public class CharacterMapperTest {
    @Autowired
    private CharacterMapper mapper;

    @Test
    public void should_map_toEntity() {
        CharacterDto dto = CharacterDtoMother.random();

        CharacterEntity actual = mapper.fromCharacterDto(dto);

        assertThat(actual).hasNoNullFieldsOrProperties();
    }

    @Test
    public void should_map_toDto() {
        CharacterEntity entity = CharacterEntityMother.random();
        entity.setFilms(List.of(FilmEntityMother.basicRandom()));
        entity.setId(10l);
        CharacterDto actual = mapper.toCharacterDto(entity);

        assertThat(actual).hasNoNullFieldsOrProperties();
    }
}
