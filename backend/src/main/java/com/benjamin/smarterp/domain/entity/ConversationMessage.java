package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TAB_CONVERSATION_MESSAGE")
public class ConversationMessage {

    @Id
    @Column(name = "ID")
    private String id;

    @Lob
    @Column(name = "MESSAGE", columnDefinition = "MEDIUMTEXT")
    private String message;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "CONVERSATION_ID")
    private Conversation conversation;

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
