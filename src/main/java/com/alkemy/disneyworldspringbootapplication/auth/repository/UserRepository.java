package com.alkemy.disneyworldspringbootapplication.auth.repository;

import com.alkemy.disneyworldspringbootapplication.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String userName);
}
