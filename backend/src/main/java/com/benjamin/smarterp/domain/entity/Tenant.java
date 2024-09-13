package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

/**
 * 租客
 */
@Entity
@Table(name = "TAB_TENANT")
public class Tenant extends BasicEntity{

    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personnel personnel;

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
