package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TAB_USER_LOGIN_PASSWORD_HISTORY")
public class UserLoginPasswordHistory {

    @Builder
    public UserLoginPasswordHistory(String currentPassword, LocalDateTime fromDate, Integer id, LocalDateTime thruDate, Integer userLoginId) {
        this.currentPassword = currentPassword;
        this.fromDate = fromDate;
        this.id = id;
        this.thruDate = thruDate;
        this.userLoginId = userLoginId;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_LOGIN_ID",nullable = false)
    private Integer userLoginId;

    /**
     * 密码生效时间
     */
    @CreatedDate
    @Column(name = "FROM_DATE",nullable = false)
    private LocalDateTime fromDate;

    /**
     * 密码失效时间
     */
    @Column(name = "THRU_DATE")
    private LocalDateTime thruDate;

    @Column(name = "CURRENT_PASSWORD")
    private String currentPassword;
}
