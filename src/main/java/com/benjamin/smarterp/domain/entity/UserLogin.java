package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TAB_USER_LOGIN")
public class UserLogin {

    @Builder
    public UserLogin(String username, String password, Boolean enabled,Boolean isSystem, Set<Authorities> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.isSystem = isSystem;
    }

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
}
