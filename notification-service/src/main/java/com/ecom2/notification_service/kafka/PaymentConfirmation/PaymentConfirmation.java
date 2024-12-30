package com.ecom2.notification_service.kafka.PaymentConfirmation;

import com.ecom2.notification_service.kafka.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
