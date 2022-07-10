package com.alkemy.disneyworldspringbootapplication.service;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CharacterService {
    CharacterDto save(CharacterDto characterDto);
    Optional<CharacterDto> update(CharacterDto characterDto);
    void remove(Long id);
    Optional<CharacterDto> findById(Long id);

    List<CharacterDto> findAllByFilter(String id, String name, String age, String weight, Set<Long> movies);

}
