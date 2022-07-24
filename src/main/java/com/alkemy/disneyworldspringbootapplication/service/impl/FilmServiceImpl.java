package com.alkemy.disneyworldspringbootapplication.service.impl;

import com.alkemy.disneyworldspringbootapplication.dto.CharacterDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmFilterDto;
import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import com.alkemy.disneyworldspringbootapplication.mapper.CharacterMapper;
import com.alkemy.disneyworldspringbootapplication.mapper.FilmMapper;
import com.alkemy.disneyworldspringbootapplication.repository.CharacterRepository;
import com.alkemy.disneyworldspringbootapplication.repository.FilmRepository;
import com.alkemy.disneyworldspringbootapplication.repository.specification.FilmSpecification;
import com.alkemy.disneyworldspringbootapplication.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final CharacterRepository characterRepository;
    private final FilmMapper filmMapper;
    private final CharacterMapper characterMapper;
    private final FilmSpecification specification;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, CharacterRepository characterRepository, FilmMapper filmMapper, CharacterMapper characterMapper, FilmSpecification specification) {
        this.filmRepository = filmRepository;
        this.characterRepository = characterRepository;
        this.filmMapper = filmMapper;
        this.characterMapper = characterMapper;
        this.specification = specification;
    }

    @Override
    public FilmDto save(FilmDto filmDto) {
        FilmEntity entity = filmMapper.fromFilmDto(filmDto);
        FilmEntity filmSaved = filmRepository.save(entity);
        return filmMapper.toFilmDto(filmSaved);
    }

    @Override
    public Optional<FilmDto> update(FilmDto filmDto) {
        Optional<FilmDto> filmSaved = findById(filmDto.getId());

        if (filmSaved.isEmpty()) {
            return filmSaved;
        }

        FilmDto film = filmSaved.get();
        film.setImage(filmDto.getImage());
        film.setCreationDate(filmDto.getCreationDate());
        film.setTitle(filmDto.getTitle());
        film.setRating(filmDto.getRating());
        FilmEntity filmUpdated = filmRepository.save(filmMapper.fromFilmDto(film));
        return Optional.of(filmMapper.toFilmDto(filmUpdated));
    }

    @Override
    public void remove(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Optional<FilmDto> findById(Long id) {
        Optional<FilmEntity> savedFilm = filmRepository.findById(id);

        return savedFilm.map(filmMapper::toFilmDto);
    }

    @Override
    public List<FilmDto> findAllByFilter(FilmFilterDto filterDto) {
        List<FilmEntity> filmEntities = filmRepository.findAll(this.specification.getByFilters(filterDto));

        return filmMapper.toFilmDtoList(filmEntities);
    }

    @Override
    public Optional<FilmDto> addCharacterIntoFilm(Long idMovie, Long idCharacter) {
        Optional<FilmDto> filmDto = findById(idMovie);
        Optional<CharacterEntity> characterEntity = characterRepository.findById(idCharacter);

        if(filmDto.isEmpty() || characterEntity.isEmpty()){
            return Optional.empty();
        }

        CharacterDto characterDto = characterMapper.toCharacterDto(characterEntity.get());
        filmDto.get().addCharacter(characterMapper.characterDtoToBasic(characterDto));

        FilmEntity updatedFilm = filmRepository.save(filmMapper.fromFilmDto(filmDto.get()));

        return Optional.of(filmMapper.toFilmDto(updatedFilm));
    }

    @Override
    public Optional<FilmDto> removeCharacterIntoFilm(Long idMovie, Long idCharacter) {
        Optional<FilmDto> filmDto = findById(idMovie);
        Optional<CharacterEntity> characterEntity = characterRepository.findById(idCharacter);

        if(filmDto.isPresent() && characterEntity.isPresent()) {
            CharacterDto characterDto = characterMapper.toCharacterDto(characterEntity.get());
            filmDto.get().removeCharacter(characterMapper.characterDtoToBasic(characterDto));

            FilmEntity savedFilm = filmRepository.save(filmMapper.fromFilmDto(filmDto.get()));

            FilmDto updatedFilm = filmMapper.toFilmDto(savedFilm);

            return Optional.of(updatedFilm);
        } else {
            return Optional.empty();
        }
    }
}
