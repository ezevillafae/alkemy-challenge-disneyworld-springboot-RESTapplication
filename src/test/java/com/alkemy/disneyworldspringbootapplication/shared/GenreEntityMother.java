package com.alkemy.disneyworldspringbootapplication.shared;

import com.alkemy.disneyworldspringbootapplication.entity.GenreEntity;

public class GenreEntityMother {
    public static GenreEntity random() {
        GenreEntity entity = new GenreEntity();
        entity.setName("Adventure");
        entity.setImage("img/random-image.jpg");
        entity.getFilms().add(FilmEntityMother.random());
        return entity;
    }
}
