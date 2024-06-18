package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserLoginHistoryRepository extends JpaRepositoryImplementation<UserLoginHistory,Integer> {
}
