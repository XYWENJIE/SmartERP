package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TAB_USER")
public class UserInfo {

    @Builder
    public UserInfo(String username, String password, Boolean enabled, Set<Authorities> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled = Boolean.FALSE;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERNAME")
    private Set<Authorities> authorities;

    @OneToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personnel personnel;
}
