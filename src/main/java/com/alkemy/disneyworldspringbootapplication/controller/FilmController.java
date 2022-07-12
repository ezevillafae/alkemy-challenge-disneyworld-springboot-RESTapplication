package com.alkemy.disneyworldspringbootapplication.controller;

import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmFilterDto;
import com.alkemy.disneyworldspringbootapplication.repository.specification.FilmSpecification;
import com.alkemy.disneyworldspringbootapplication.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/movies")
public class FilmController {

    @Autowired
    private FilmService service;

    @PostMapping
    public ResponseEntity<FilmDto> save(@RequestBody FilmDto filmDto) {
        FilmDto savedFilm = service.save(filmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
    }

    @PostMapping("{idMovie}/characters/{idCharacter}")
    public ResponseEntity<FilmDto> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        Optional<FilmDto> updatedFilm = service.addCharacterIntoFilm(idMovie, idCharacter);
        return updatedFilm
                .map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<FilmDto> update(@RequestBody FilmDto filmDto) {
        Optional<FilmDto> updatedFilm = service.update(filmDto);

        return updatedFilm
                .map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("{id}")
    public ResponseEntity<FilmDto> getById(@PathVariable Long id) {
        Optional<FilmDto> film = service.findById(id);

        return film
                .map(f -> ResponseEntity.status(HttpStatus.OK).body(f))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllByFilter(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "genre", required = false) Set<Long> idGenres,
            @RequestParam(value = "order", required = false) String order
    ) {
        FilmFilterDto film = new FilmFilterDto(id, name, idGenres, order);
        List<FilmDto> films = service.findAllByFilter(film);

        return ResponseEntity.ok(films);
    }

    @DeleteMapping("{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Object> removeCharacterIntoFilm(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        service.removeCharacterIntoFilm(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
