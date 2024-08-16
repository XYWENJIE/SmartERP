package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TAB_USER_LOGIN_PASSWORD_HISTORY")
public class UserLoginPasswordHistory {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_LOGIN_ID",nullable = false)
    private Integer userLoginId;

    /**
     * 密码生效时间
     */
    @CreatedDate
    @Column(name = "FROM_DATE",nullable = false)
    private LocalDateTime fromDate = LocalDateTime.now();

    /**
     * 密码失效时间
     */
    @Column(name = "THRU_DATE")
    private LocalDateTime thruDate;

    @Column(name = "CURRENT_PASSWORD")
    private String currentPassword;

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

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getThruDate() {
		return thruDate;
	}

	public void setThruDate(LocalDateTime thruDate) {
		this.thruDate = thruDate;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
    
    
}
