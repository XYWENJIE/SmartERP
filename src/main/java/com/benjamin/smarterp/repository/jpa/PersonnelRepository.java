package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.Personnel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface PersonnelRepository extends JpaRepositoryImplementation<Personnel,Integer> {
}
