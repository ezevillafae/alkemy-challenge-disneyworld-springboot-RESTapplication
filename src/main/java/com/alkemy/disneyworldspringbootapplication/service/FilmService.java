package com.alkemy.disneyworldspringbootapplication.service;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmFilterDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmService {
    FilmDto save(FilmDto filmDto);

    Optional<FilmDto> update(FilmDto filmDto);

    void remove(Long id);

    Optional<FilmDto> findById(Long id);

    List<FilmDto> findAllByFilter(FilmFilterDto filterDto);

    Optional<FilmDto> addCharacterIntoFilm(Long idMovie, Long idCharacter);

    void removeCharacterIntoFilm(Long idMovie, Long idCharacter);
}
