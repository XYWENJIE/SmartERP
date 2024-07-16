package com.benjamin.smarterp.domain.entity;

import com.benjamin.smarterp.domain.entity.type.TeamType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TAB_TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Enumerated(EnumType.STRING)
    @Column(name = "TEAM_TYPE")
    private TeamType teamType;
}
