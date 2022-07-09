package com.alkemy.disneyworldspringbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "film_character",
            joinColumns = @JoinColumn(name = "id_character", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id")
    )
    private List<FilmEntity> films = new ArrayList<>();

    public void addFilm(FilmEntity entity) {
        films.add(entity);
    }

    public void removeFilm(FilmEntity entity) {
        films.remove(entity);
    }
}
