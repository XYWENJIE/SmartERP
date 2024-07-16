package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TAB_CHAT_MESSAGE")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SEND_TIME")
    private LocalDateTime sendTime;

}
