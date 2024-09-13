package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TAB_USER_LOGIN_HISTORY")
public class UserLoginHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "USER_LOGIN_ID",nullable = false)
	private Integer userLoginId;

	@CreatedDate
	@Column(name = "FORM_DATE",nullable = false)
	private LocalDateTime formDate;

	@Column(name = "REMOTE_HOST")
	private String remoteHost;

	@Column(name = "SERVER_ROOT_URL")
	private String serverRootUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(Integer userLoginId) {
		this.userLoginId = userLoginId;
	}

	public LocalDateTime getFormDate() {
		return formDate;
	}

	public void setFormDate(LocalDateTime formDate) {
		this.formDate = formDate;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getServerRootUrl() {
		return serverRootUrl;
	}

	public void setServerRootUrl(String serverRootUrl) {
		this.serverRootUrl = serverRootUrl;
	}
}
