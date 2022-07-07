package com.alkemy.disneyworldspringbootapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Data
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;

    @ManyToMany(mappedBy = "characters")
    private List<FilmEntity> films = new ArrayList<>();
}
