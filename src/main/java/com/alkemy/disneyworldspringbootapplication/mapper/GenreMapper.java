package com.alkemy.disneyworldspringbootapplication.mapper;

import com.alkemy.disneyworldspringbootapplication.dto.GenreDto;
import com.alkemy.disneyworldspringbootapplication.entity.GenreEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {
    GenreDto toGenreDto(GenreEntity entity);
    GenreEntity fromGenreDto(GenreDto genreDto);
}
