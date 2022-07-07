package com.alkemy.disneyworldspringbootapplication.mapper;


import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import com.alkemy.disneyworldspringbootapplication.shared.FilmDtoMother;
import com.alkemy.disneyworldspringbootapplication.shared.FilmEntityMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        FilmMapperImpl.class
})
public class FilmMapperTest {

    @Autowired
    private FilmMapper mapper;

    @Test
    public void mapper_not_null() {
        assertThat(mapper).isNotNull();
    }

    @Test
    public void should_map_toDto(){
        FilmEntity entity = FilmEntityMother.random();
        entity.setId(10l);

        FilmDto actual = mapper.toFilmDto(entity);

        assertThat(actual).hasNoNullFieldsOrProperties();
    }

    @Test
    public void should_map_toEntity() {
        FilmDto dto = FilmDtoMother.random();

        FilmEntity actual = mapper.fromFilmDto(dto);

        assertThat(actual).hasNoNullFieldsOrProperties();
    }

}
