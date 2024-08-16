package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "TAB_USER_LOGIN")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERNAME",unique = true,nullable = false)
    private String username;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "ENABLED",nullable = false)
    private Boolean enabled = Boolean.FALSE;

    @Column(name = "IS_SYSTEM",nullable = false)
    private Boolean isSystem;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_LOGIN_ID")
    private Set<Authorities> authorities;

    @OneToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personnel personnel;

    @OneToMany
    @JoinColumn(name = "USER_LOGIN_ID")
    private List<UserLoginHistory> userLoginHistories;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public List<UserLoginHistory> getUserLoginHistories() {
		return userLoginHistories;
	}

	public void setUserLoginHistories(List<UserLoginHistory> userLoginHistories) {
		this.userLoginHistories = userLoginHistories;
	}
    
    
}
