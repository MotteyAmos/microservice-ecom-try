package com.ecom2.notification_service.email;

import com.ecom2.notification_service.kafka.payment.PaymentConfirmation;
import lombok.Getter;

public enum EmailTemplates {


    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment_successfully precessed"),

    ORDER_CONFIRMATION("order-confirmation.html", "Order_confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject){
        this.template = template;
        this.subject = subject;
    }
    }
