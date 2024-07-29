package com.benjamin.smarterp.repository.jpa;

import com.benjamin.smarterp.domain.entity.Notification;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface NotificationRepository extends JpaRepositoryImplementation<Notification,Integer> {

    Optional<Notification> findBySenderUserIdAndType(Integer senderUserId, Notification.Type type);
}
