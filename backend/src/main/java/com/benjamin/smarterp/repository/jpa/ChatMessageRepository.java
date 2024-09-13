package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.ChatMessage;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ChatMessageRepository extends JpaRepositoryImplementation<ChatMessage,Integer> {
}
