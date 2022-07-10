package com.alkemy.disneyworldspringbootapplication.service.impl;

import com.alkemy.disneyworldspringbootapplication.dto.GenreDto;
import com.alkemy.disneyworldspringbootapplication.entity.GenreEntity;
import com.alkemy.disneyworldspringbootapplication.mapper.GenreMapper;
import com.alkemy.disneyworldspringbootapplication.repository.GenreRepository;
import com.alkemy.disneyworldspringbootapplication.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository repository;

    @Autowired
    private GenreMapper mapper;


    @Override
    public GenreDto save(GenreDto genre) {
        GenreEntity genreEntity = mapper.fromGenreDto(genre);
        GenreEntity savedGenre = repository.save(genreEntity);

        return mapper.toGenreDto(savedGenre);
    }
}
