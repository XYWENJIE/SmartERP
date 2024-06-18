package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.UserLoginPasswordHistory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface UserLoginPasswordHistoryRepository extends JpaRepositoryImplementation<UserLoginPasswordHistory,Integer> {

    Optional<UserLoginPasswordHistory> findByCurrentPassword(String currentPassword);
}
