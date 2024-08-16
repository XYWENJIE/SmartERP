package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.UserLogin;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepositoryImplementation<UserLogin,Integer> {

    Optional<UserLogin> findByUsername(String username);

    Boolean existsByUsername(String username);
}
