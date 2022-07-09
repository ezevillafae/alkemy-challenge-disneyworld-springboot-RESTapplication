package com.alkemy.disneyworldspringbootapplication.service;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    CharacterDto save(CharacterDto characterDto);
    Optional<CharacterDto> update(CharacterDto characterDto);
    void remove(Long id);
    Optional<CharacterDto> findById(Long id);

    List<CharacterDto> findAll();
}
