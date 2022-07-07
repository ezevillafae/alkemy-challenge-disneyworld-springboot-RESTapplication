package com.alkemy.disneyworldspringbootapplication.mapper;

import com.alkemy.disneyworldspringbootapplication.dto.GenreDto;
import com.alkemy.disneyworldspringbootapplication.entity.GenreEntity;
import com.alkemy.disneyworldspringbootapplication.shared.GenreEntityMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        GenreMapperImpl.class
})
public class GenreMapperTest {
    @Autowired
    private GenreMapper mapper;


    @Test
    public void mapper_not_null() {
        assertThat(mapper).isNotNull();
    }

    @Test
    public void should_map_toGenreDto() {
        GenreEntity entity = GenreEntityMother.random();
        entity.setId(10l);
        GenreDto actual = mapper.toGenreDto(entity);

        assertThat(actual).hasNoNullFieldsOrProperties();
    }
}
