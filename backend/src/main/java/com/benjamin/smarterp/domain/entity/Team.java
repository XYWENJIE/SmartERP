package com.benjamin.smarterp.domain.entity;

import com.benjamin.smarterp.domain.entity.type.TeamType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TAB_TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEAM_TYPE")
    private TeamType teamType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public TeamType getTeamType() {
		return teamType;
	}

	public void setTeamType(TeamType teamType) {
		this.teamType = teamType;
	}
    
    
}
