package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

/**
 * 业主类
 */
@Entity
@Table(name = "TAB_OWNER")
public class Owner extends BasicEntity{

    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personnel personnel;

    @OneToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


}
