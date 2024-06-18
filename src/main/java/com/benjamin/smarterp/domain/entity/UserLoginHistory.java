package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TAB_USER_LOGIN_HISTORY")
public class UserLoginHistory {

	@Builder
	public UserLoginHistory(LocalDateTime formDate, Integer id, String remoteHost, String serverRootUrl, Integer userLoginId) {
		this.formDate = formDate;
		this.id = id;
		this.remoteHost = remoteHost;
		this.serverRootUrl = serverRootUrl;
		this.userLoginId = userLoginId;
	}

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

}
