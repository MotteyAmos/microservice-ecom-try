package com.ecom2.notification_service.kafka.payment;

import com.ecom2.notification_service.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
