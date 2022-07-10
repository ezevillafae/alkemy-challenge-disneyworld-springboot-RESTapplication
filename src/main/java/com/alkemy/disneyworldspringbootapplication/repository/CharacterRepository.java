package com.alkemy.disneyworldspringbootapplication.repository;

import com.alkemy.disneyworldspringbootapplication.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {
    @Override
    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}
