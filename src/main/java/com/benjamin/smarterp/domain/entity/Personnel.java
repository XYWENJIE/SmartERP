package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 人员表
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TAB_PERSONNEL")
public class Personnel {

    @Builder
    public Personnel(Integer id,String realName, String email,String avatarUrl) {
        this.id = id;
        this.realName = realName;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

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

    @ManyToOne
    @JoinColumn(name = "USER_LOGIN_ID")
    private UserLogin userLogin;

    @ManyToMany
    @JoinTable(name = "TAB_USER_TEAM",joinColumns = @JoinColumn(name = "USER_ID"),inverseJoinColumns = @JoinColumn(name = "TEAM_ID"))
    private List<Team> teams;

}
