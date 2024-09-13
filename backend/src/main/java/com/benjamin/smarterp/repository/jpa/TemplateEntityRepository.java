package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.domain.entity.TemplateEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface TemplateEntityRepository extends JpaRepositoryImplementation<TemplateEntity,String> {

    Optional<TemplateEntity> findByTeamAndName(Team team, String name);
}
