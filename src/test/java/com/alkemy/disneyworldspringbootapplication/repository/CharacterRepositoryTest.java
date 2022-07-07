package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.shared.CharacterEntityMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CharacterRepositoryTest {
    @Autowired
    private CharacterRepository repository;

    @Test
    public void should_create_character(){
        CharacterEntity character = CharacterEntityMother.random();

        CharacterEntity actual = repository.save(character);

        assertThat(actual).hasNoNullFieldsOrPropertiesExcept("films");
    }
}
