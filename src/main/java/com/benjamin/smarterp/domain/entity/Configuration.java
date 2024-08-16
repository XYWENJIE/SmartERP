package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TAB_CONFIGURATION")
public class Configuration extends BasicEntity{

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "CONFIG_VALUE",nullable = false)
    private String value;

    @Column(name = "NOTE",nullable = false)
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
