package com.alkemy.disneyworldspringbootapplication.controller;

import com.alkemy.disneyworldspringbootapplication.dto.GenreDto;
import com.alkemy.disneyworldspringbootapplication.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService service;

    @PostMapping
    public ResponseEntity<GenreDto> save(@RequestBody GenreDto genreDto) {
        GenreDto genreSaved = service.save(genreDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }
}
