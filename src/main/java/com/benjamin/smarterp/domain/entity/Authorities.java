package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TAB_AUTHORITIES")
public class Authorities {

    @Builder
    public Authorities(Integer id,Integer userLoginId, String username, String authority) {
        this.id = id;
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
}
