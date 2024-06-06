package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人员表
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TAB_PERSONNEL")
public class Personnel {

    @Builder
    public Personnel(Integer id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

}
