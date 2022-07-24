package com.alkemy.disneyworldspringbootapplication.service.impl;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.CharacterFilterDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.mapper.CharacterMapper;
import com.alkemy.disneyworldspringbootapplication.repository.CharacterRepository;
import com.alkemy.disneyworldspringbootapplication.repository.specification.CharacterSpecification;
import com.alkemy.disneyworldspringbootapplication.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository repository;

    private final CharacterMapper mapper;

    private final CharacterSpecification specification;

    @Autowired
    public CharacterServiceImpl(CharacterRepository repository, CharacterMapper mapper, CharacterSpecification specification) {
        this.repository = repository;
        this.mapper = mapper;
        this.specification = specification;
    }

    @Override
    public CharacterDto save(CharacterDto characterDto) {
        CharacterEntity entity = mapper.fromCharacterDto(characterDto);
        CharacterEntity characterSaved = repository.save(entity);
        return mapper.toCharacterDto(characterSaved);
    }

    @Override
    public Optional<CharacterDto> update(CharacterDto characterDto) {
        Optional<CharacterEntity> character = repository.findById(characterDto.getId());

        if (character.isEmpty()) {
            return Optional.empty();
        }

        CharacterEntity entity = character.get();
        entity.setImage(characterDto.getImage());
        entity.setWeight(characterDto.getWeight());
        entity.setAge(characterDto.getAge());
        entity.setHistory(characterDto.getHistory());
        entity.setName(characterDto.getName());
        repository.save(entity);
        return Optional.of(mapper.toCharacterDto(character.get()));
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<CharacterDto> findById(Long id) {
        Optional<CharacterEntity> savedCharacter = repository.findById(id);

        return savedCharacter.map(mapper::toCharacterDto);
    }

    @Override
    public List<CharacterDto> findAllByFilter(CharacterFilterDto characterFilterDto) {
        List<CharacterEntity> characterEntities = repository.findAll(this.specification.getByFilters(characterFilterDto));
        return mapper.toCharacterDtoList(characterEntities);
    }
}
