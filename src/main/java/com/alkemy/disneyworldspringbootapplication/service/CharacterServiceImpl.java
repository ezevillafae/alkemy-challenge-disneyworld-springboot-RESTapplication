package com.alkemy.disneyworldspringbootapplication.service;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmBasicDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.mapper.CharacterMapper;
import com.alkemy.disneyworldspringbootapplication.mapper.FilmMapper;
import com.alkemy.disneyworldspringbootapplication.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterMapper mapper;


    @Override
    public CharacterDto save(CharacterDto characterDto) {
        CharacterEntity entity = mapper.fromCharacterDto(characterDto);
        CharacterEntity characterSaved = repository.save(entity);
        return mapper.toCharacterDto(characterSaved);
    }


    @Override
    public Optional<CharacterDto> update(CharacterDto characterDto) {
        Optional<CharacterEntity> character = repository.findById(characterDto.getId());
        if(character.isPresent()){
            CharacterEntity entity = character.get();
            entity.setImage(characterDto.getImage());
            entity.setWeight(characterDto.getWeight());
            entity.setAge(characterDto.getAge());
            entity.setHistory(characterDto.getHistory());
            entity.setName(characterDto.getName());
            repository.save(entity);
            return Optional.of(mapper.toCharacterDto(character.get()));
        }else{
            return Optional.empty();
        }

    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);
    }

    @Override
    public Optional<CharacterDto> findById(Long id) {
        Optional<CharacterEntity> savedCharacter = repository.findById(id);

        return savedCharacter.map(entity -> mapper.toCharacterDto(entity));
    }

    @Override
    public List<CharacterDto> findAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.toCharacterDto(entity))
                .collect(Collectors.toList());
    }


}
