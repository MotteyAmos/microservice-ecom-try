package com.ecom2.notification_service.notification;


import com.ecom2.notification_service.kafka.OrderConfirmation.OrderConfirmation;
import com.ecom2.notification_service.kafka.PaymentConfirmation.PaymentConfirmation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
