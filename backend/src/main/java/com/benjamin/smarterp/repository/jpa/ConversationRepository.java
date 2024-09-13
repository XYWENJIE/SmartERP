package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.Conversation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ConversationRepository extends JpaRepositoryImplementation<Conversation,String> {
}
