package com.alkemy.disneyworldspringbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
@Data
public class FilmEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String title;

    @Column(name = "creation_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    private Double rating;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "film_character",
            joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_character", referencedColumnName = "id")
    )
    private List<CharacterEntity> characters = new ArrayList<>();

    @ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "genre_film",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private List<GenreEntity> genres = new ArrayList<>();

    public void addCharacter(CharacterEntity entity) {
        characters.add(entity);
    }

    public void removeCharacter(CharacterEntity entity) {
        characters.remove(entity);
    }

    public void addGenre(GenreEntity entity) {
        genres.add(entity);
    }

    public void removeGenre(GenreEntity entity) {
        genres.remove(entity);
    }
}
