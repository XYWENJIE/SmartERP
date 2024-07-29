package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "TAB_NOTIFICATION")
public class Notification {

    public Notification() {
    }

    @Builder
    public Notification(String content, Integer id, UserLogin receiverUser, UserLogin senderUser, LocalDateTime sendTime, Status status, Type type) {
        this.content = content;
        this.id = id;
        this.receiverUser = receiverUser;
        this.senderUser = senderUser;
        this.sendTime = sendTime;
        this.status = status;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID",nullable = false)
    private UserLogin senderUser;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID",nullable = false)
    private UserLogin receiverUser;

    @Column(name = "CONTENT",nullable = false)
    private String content;

    @Column(name = "SEND_TIME",nullable = false)
    private LocalDateTime sendTime = LocalDateTime.now();

    @Column(name = "STATUS",nullable = false)
    private Status status;

    @Column(name = "TYPE",nullable = false)
    private Type type;

    public enum Status{
        UNREAD,READ,DELETED
    }

    public enum Type{
        MESSAGE,ALERT,TASK,CHAT
    }

}
