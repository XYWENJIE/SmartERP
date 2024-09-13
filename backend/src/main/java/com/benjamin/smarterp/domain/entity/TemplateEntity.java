package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

/**
 * 模板功能。主要保存合同以及未来商业合同模板等
 */
@Entity
@Table(name = "TAB_TEMPLATE")
public class TemplateEntity extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "FORMAT")
    private String format;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TEMPLATE_VALUE")
    private String value;

    @Column(name = "DESCRIPTION")
    private String description;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
