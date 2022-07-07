package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
