package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.dto.FilmBasicDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;

import java.util.ArrayList;
import java.util.List;

public class FilmEntityMother {
    public static FilmEntity random(){
        FilmEntity film = new FilmEntity();
        film.setTitle("Film random");
        film.getCharacters().add(CharacterEntityMother.random());
        film.setCreationDate(LocalDateMother.random());
        film.setImage("img/random-image.jpg");
        film.setRating(DoubleMother.random());
        return film;
    }

    public static FilmEntity basicRandom() {
        FilmEntity film = new FilmEntity();
        film.setTitle("Nueva pelicula");
        film.setCreationDate(LocalDateMother.random());
        film.setImage("img/random-image.jpg");
        film.setRating(DoubleMother.random());
        return film;
    }
}
