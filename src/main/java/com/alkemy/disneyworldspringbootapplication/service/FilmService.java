package com.alkemy.disneyworldspringbootapplication.service;

import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmFilterDto;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    FilmDto save(FilmDto filmDto);

    Optional<FilmDto> update(FilmDto filmDto);

    void remove(Long id);

    Optional<FilmDto> findById(Long id);

    List<FilmDto> findAllByFilter(FilmFilterDto filterDto);

    Optional<FilmDto> addCharacterIntoFilm(Long idMovie, Long idCharacter);

    void removeCharacterIntoFilm(Long idMovie, Long idCharacter);
}
