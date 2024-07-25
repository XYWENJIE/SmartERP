package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.ConversationMessage;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ConversationMessageRepository extends JpaRepositoryImplementation<ConversationMessage,String> {
}
