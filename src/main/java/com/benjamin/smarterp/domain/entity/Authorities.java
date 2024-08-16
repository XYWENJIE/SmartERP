package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TAB_AUTHORITIES")
public class Authorities {
	
	public Authorities() {
		
	}
	
	public Authorities(Integer userLoginId,String username,String authority) {
		this.userLoginId = userLoginId;
		this.username = username;
		this.authority = authority;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_LOGIN_ID")
    private Integer userLoginId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "AUTHORITY")
    private String authority;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
    
    
}
