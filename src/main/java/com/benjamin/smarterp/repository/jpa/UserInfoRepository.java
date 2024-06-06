package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepositoryImplementation<UserInfo,Integer> {

    Optional<UserInfo> findByUsername(String username);

    Boolean existsByUsername(String username);
}
