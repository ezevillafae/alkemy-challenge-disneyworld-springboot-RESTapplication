package com.alkemy.disneyworldspringbootapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
@Data
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;

    @ManyToMany(mappedBy = "genres")
    private List<FilmEntity> films = new ArrayList<>();

    public void addFilm(FilmEntity entity) {
        films.add(entity);
    }

    public void removeFilm(FilmEntity entity) {
        films.remove(entity);
    }
}
