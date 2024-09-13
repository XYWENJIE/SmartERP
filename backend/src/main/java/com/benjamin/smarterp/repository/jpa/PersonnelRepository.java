package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.domain.entity.UserLogin;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
import java.util.Optional;

public interface PersonnelRepository extends JpaRepositoryImplementation<Personnel,String> {

    boolean existsByRealName(String realName);

    Optional<Personnel> findByUserLogin(UserLogin userLogin);

    List<Personnel> findByRobot(boolean robot);

    List<Personnel> findByTeams(List<Team> teams);
}
