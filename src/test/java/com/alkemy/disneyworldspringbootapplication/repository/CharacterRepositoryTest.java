package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.shared.CharacterEntityMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CharacterRepositoryTest {
    @Autowired
    private CharacterRepository repository;

    @Test
    @Rollback(value = false)
    public void should_create_character(){
        CharacterEntity character = CharacterEntityMother.random();
        character.setId(17l);

        CharacterEntity actual = repository.saveAndFlush(character);
        Long id = actual.getId();
        Optional<CharacterEntity> characterEntity = repository.findById(id);

        assertThat(characterEntity).isPresent();
        assertThat(characterEntity.get()).hasNoNullFieldsOrProperties();
    }


    @Test
    @Rollback(value = false)
    public void should_not_update_film(){
        Optional<CharacterEntity> entity = repository.findById(14l);
        assertThat(entity).isPresent();

        entity.get().getFilms().get(0).setTitle("titulo");
    }
}
