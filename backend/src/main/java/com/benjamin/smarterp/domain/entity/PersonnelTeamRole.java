package com.benjamin.smarterp.domain.entity;

import com.benjamin.smarterp.domain.entity.type.PersonnelRole;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TAB_RERSONNEL_TEAM_ROLE")
public class PersonnelTeamRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID",nullable = false)
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "TEAM")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE",nullable = false)
    private PersonnelRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PersonnelRole getRole() {
        return role;
    }

    public void setRole(PersonnelRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnelTeamRole that = (PersonnelTeamRole) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
