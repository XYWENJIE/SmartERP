package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TAB_CONVERSATIONL_PARTICAIPATION")
public class ConversationParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "CPMVERSATOPM_ID")
    private Conversation conversation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
    
    

}
