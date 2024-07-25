package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
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

}
