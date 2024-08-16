package com.benjamin.smarterp.domain.entity;

import com.benjamin.smarterp.domain.entity.type.PersonnelRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员表
 */
@Entity
@Table(name = "TAB_PERSONNEL")
public class Personnel extends BasicEntity {


    @Column(name = "REAL_NAME")
    private String realName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @Column(name = "ROBOT")
    private Boolean robot;

	@OneToMany(mappedBy = "personnel",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<PersonnelTeamRole> personnelTeamRoles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_LOGIN_ID")
    private UserLogin userLogin;

    @ManyToMany
    @JoinTable(name = "TAB_USER_TEAM",joinColumns = @JoinColumn(name = "PERSONNEL_ID"),inverseJoinColumns = @JoinColumn(name = "TEAM_ID"))
    private List<Team> teams = new ArrayList<>();

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Boolean getRobot() {
		return robot;
	}

	public void setRobot(Boolean robot) {
		this.robot = robot;
	}

	public List<PersonnelTeamRole> getPersonnelTeamRoles() {
		return personnelTeamRoles;
	}

	public void setPersonnelTeamRoles(List<PersonnelTeamRole> personnelTeamRoles) {
		this.personnelTeamRoles = personnelTeamRoles;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
    
    

}
