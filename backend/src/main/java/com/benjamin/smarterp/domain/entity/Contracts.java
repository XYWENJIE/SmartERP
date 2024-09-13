package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

/**
 * 合同数据类
 */
@Entity(name = "TAB_CONTRACTS")
public class Contracts {

    @Id
    @Column(name = "ID")
    private Integer id;

    private Status status;

    private LocalDate createdAt = LocalDate.now();

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{
        ACTIVE,INACTIVE,EXPIRED
    }

}
