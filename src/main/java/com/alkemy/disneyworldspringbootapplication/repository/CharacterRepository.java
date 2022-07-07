package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}
