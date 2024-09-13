package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 员工类实体
 * 涉及到公司员工特有信息，比如工号，社保状态等
 */
@Getter
@Setter
@Entity
@Table(name = "TAB_EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID")
    public Personnel personnel;
}
