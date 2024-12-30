package com.ecom2.notification_service.kafka;


import com.ecom2.notification_service.email.EmailService;
import com.ecom2.notification_service.kafka.OrderConfirmation.OrderConfirmation;
import com.ecom2.notification_service.kafka.payment.NotificationRepository;

import com. ecom2.notification_service. kafka. PaymentConfirmation. PaymentConfirmation;
import com.ecom2.notification_service.notification.Notification;
import com.ecom2.notification_service.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
            log.info(String.format("Cusming the message from payment-topic Topic:: %s", paymentConfirmation));

            repository.save(
                    Notification.builder()
                            .type(NotificationType.PAYMENT_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                            .paymentConfirmation(paymentConfirmation)
                            .build()
            );

            var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation ));
        repository
                .save(
                        Notification.builder()
                                .type(NotificationType.ORDER_CONFIRMATION)
                                .notificationDate(LocalDateTime.now())
                                .orderConfirmation(orderConfirmation)
                                .build()
                );

        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }


}
