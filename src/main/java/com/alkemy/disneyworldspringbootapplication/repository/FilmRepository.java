package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmRepository extends JpaRepository<FilmEntity, Long>, JpaSpecificationExecutor<FilmEntity> {
}
