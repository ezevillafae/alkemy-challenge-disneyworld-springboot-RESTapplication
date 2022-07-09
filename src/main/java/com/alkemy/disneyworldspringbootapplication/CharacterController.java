package com.alkemy.disneyworldspringbootapplication;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<CharacterDto> update(@RequestBody CharacterDto character) {
        Optional<CharacterDto> updatedCharacter = service.update(character);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCharacter.get());
    }

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getAllCharacters() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
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
