package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;

import java.util.List;

public class CharacterEntityMother {

    public static CharacterEntity build(String name, String history, String image, Integer age, Double weight, List<FilmEntity> movies) {
        CharacterEntity character = new CharacterEntity();
        character.setName(name);
        character.setImage(image);
        character.setHistory(history);
        character.setAge(age);
        character.setWeight(weight);
        movies.forEach(character::addFilm);
        return character;
    }

    public static CharacterEntity random() {
        CharacterEntity character = new CharacterEntity();
        character.setName("Will Smith");
        character.setAge(IntegerMother.random());
        character.setHistory("Random history");
        character.setWeight(DoubleMother.random());
        character.setImage("img/image-random.jpg");
        character.getFilms().add(FilmEntityMother.basicRandom());
        return character;
    }
}
