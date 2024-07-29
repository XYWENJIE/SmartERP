package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 谈话JPA实体类
 */
@Entity
@Table(name = "TAB_CONVERSATION")
public class Conversation {

    public Conversation() {
    }

    @Builder
    public Conversation(Set<ConversationMessage> conversationMessages, String conversationName, LocalDateTime createTime, String id, LocalDateTime lastActiveTime) {
        this.conversationMessages = conversationMessages;
        this.conversationName = conversationName;
        this.createTime = createTime;
        this.id = id;
        this.lastActiveTime = lastActiveTime;
    }

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CONVERSATION_NAME")
    private String conversationName;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "LAST_ACTIVE_TIME")
    private LocalDateTime lastActiveTime = LocalDateTime.now();

    @OneToMany
    @JoinColumn(name = "CONVERSATION_ID")
    private Set<ConversationMessage> conversationMessages;

    public Set<ConversationMessage> getConversationMessages() {
        return conversationMessages;
    }

    public void setConversationMessages(Set<ConversationMessage> conversationMessages) {
        this.conversationMessages = conversationMessages;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(LocalDateTime lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}
