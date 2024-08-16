package com.benjamin.smarterp;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.domain.entity.UserLogin;
import com.benjamin.smarterp.domain.entity.type.PersonnelRole;
import com.benjamin.smarterp.domain.entity.type.TeamType;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.repository.jpa.TeamRepository;
import com.benjamin.smarterp.repository.jpa.UserLoginRepository;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserLoginRepository userLoginRepository;
    private final PersonnelRepository personnelRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(UserLoginRepository userLoginRepository, PersonnelRepository personnelRepository, TeamRepository teamRepository, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.userLoginRepository = userLoginRepository;
        this.personnelRepository = personnelRepository;
        this.teamRepository = teamRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Team> teamList = new ArrayList<>();
        log.info("RUN");
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("admin");
        userLogin.setPassword(passwordEncoder.encode("huang1100"));
        userLogin.setIsSystem(true);
        this.userLoginRepository.save(userLogin);
        Team team = new Team();
        team.setTeamName("管理员团队");
        team.setTeamType(TeamType.SYSTEM_ADMIN);
        teamList.add(team);
        team = new Team();
        team.setTeamName("黄氏家族");
        team.setTeamType(TeamType.FAMILY);
        teamList.add(team);
        team = new Team();
        team.setTeamName("壹家播商");
        team.setTeamType(TeamType.ENTERPRISE);
        teamList.add(team);
        this.teamRepository.saveAll(teamList);
        Personnel personnel = new Personnel();
        personnel.setId(UUID.randomUUID().toString());
        personnel.setEmail("xywenjie@outlook.com");
        personnel.setRealName("黄文杰");
        personnel.setAvatarUrl("https://pub-c5e31b5cdafb419fb247a8ac2e78df7a.r2.dev/mock/assets/images/avatar/avatar-1.webp");
        personnel.setUserLogin(userLogin);
        personnel.getTeams().add(teamList.get(0));
        personnel.getTeams().add(teamList.get(1));
        personnel.getTeams().add(teamList.get(2));
        this.personnelRepository.save(personnel);
        Personnel userInfo = new Personnel();
        userInfo.setId(UUID.randomUUID().toString());
            userInfo.setRealName("SmartAssist");
            userInfo.setRobot(true);
            userInfo.setAvatarUrl("http://localhost:8080/a821c0ab-f8a0-5284-a714-679e578992f5_0.png");
        userInfo.getTeams().add(teamList.get(0));
        this.personnelRepository.save(userInfo);

        userLogin = new UserLogin();
        userLogin.setUsername("huangrenjia");
        userLogin.setPassword(passwordEncoder.encode("huang1100"));
        userLogin.setEnabled(true);
        userLogin.setIsSystem(true);
        this.userLoginRepository.save(userLogin);

        userInfo = new Personnel();
        userInfo.setId(UUID.randomUUID().toString());
        userInfo.setRealName("黄仁杰");
        userInfo.setAvatarUrl("http://localhost:8080/a821c0ab-f8a0-5284-a714-679e578992f5_0.png");
        userInfo.setUserLogin(userLogin);
        userInfo.getTeams().add(teamList.get(1));
        this.personnelRepository.save(userInfo);

    }
}
