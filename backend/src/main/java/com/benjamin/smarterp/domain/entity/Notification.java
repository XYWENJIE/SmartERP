package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TAB_NOTIFICATION")
public class Notification {

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

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserLogin getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(UserLogin senderUser) {
		this.senderUser = senderUser;
	}

	public UserLogin getReceiverUser() {
		return receiverUser;
	}

	public void setReceiverUser(UserLogin receiverUser) {
		this.receiverUser = receiverUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getSendTime() {
		return sendTime;
	}

	public void setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Status{
        UNREAD,READ,DELETED
    }

    public enum Type{
        MESSAGE,ALERT,TASK,CHAT
    }

}
