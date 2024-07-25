package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "TAB_CONVERSATION")
public class Conversation {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CONVERSATION_NAME")
    private String conversationName;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "LAST_ACTIVE_TIME")
    private LocalDateTime lastActiveTime;

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
