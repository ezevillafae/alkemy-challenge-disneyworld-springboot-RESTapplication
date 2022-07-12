package com.alkemy.disneyworldspringbootapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Data
public class CharacterEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;

    @ManyToMany(
            mappedBy = "characters"
    )
    private List<FilmEntity> films = new ArrayList<>();

    public void addFilm(FilmEntity entity) {
        films.add(entity);
    }

    public void removeFilm(FilmEntity entity) {
        films.remove(entity);
    }
}
