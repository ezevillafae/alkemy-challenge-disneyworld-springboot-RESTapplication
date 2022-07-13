package com.alkemy.disneyworldspringbootapplication.controller;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService service;

    @PostMapping
    public ResponseEntity<CharacterDto> save(@RequestBody CharacterDto character) {
        CharacterDto savedCharacter = service.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping
    public ResponseEntity<CharacterDto> update(@Valid @RequestBody CharacterDto character) {
        Optional<CharacterDto> updatedCharacter = service.update(character);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCharacter.get());
    }

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getAllCharacters(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) String age,
            @RequestParam(value = "weight", required = false) String weight,
            @RequestParam(value = "movies", required = false) Set<Long> movies
    ) {

        List<CharacterDto> characters = service.findAllByFilter(id, name, age, weight, movies);
        return ResponseEntity.ok(characters);
    }

    @GetMapping("{id}")
    public ResponseEntity<CharacterDto> getById(@PathVariable Long id) {
        Optional<CharacterDto> character = service.findById(id);
        return character.map(characterDto -> ResponseEntity.status(HttpStatus.OK).body(characterDto)).orElseGet(() -> ResponseEntity.status(HttpStatus.OK).build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.status(HttpStatus.OK).build();
        //TODO: personalize response
    }
}
