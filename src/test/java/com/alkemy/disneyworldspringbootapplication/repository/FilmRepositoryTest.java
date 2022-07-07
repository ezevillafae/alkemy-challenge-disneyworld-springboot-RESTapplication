package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import com.alkemy.disneyworldspringbootapplication.shared.FilmEntityMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository repository;

    @Test
    public void should_create_film() {
        FilmEntity film = FilmEntityMother.random();

        FilmEntity actual = repository.save(film);

        assertThat(actual).hasNoNullFieldsOrProperties();
    }
}
