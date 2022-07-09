package com.alkemy.disneyworldspringbootapplication.mapper;

import com.alkemy.disneyworldspringbootapplication.dto.FilmBasicDto;
import com.alkemy.disneyworldspringbootapplication.dto.FilmDto;
import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = CharacterMapper.class)
public interface FilmMapper {

    FilmDto toFilmDto(FilmEntity filmEntity);
    FilmEntity fromFilmDto(FilmDto filmDto);
    List<FilmDto> toFilmDtoList(List<FilmEntity> filmEntities);
    List<FilmEntity> fromFilmDtoList(List<FilmDto> filmDtoList);
}
