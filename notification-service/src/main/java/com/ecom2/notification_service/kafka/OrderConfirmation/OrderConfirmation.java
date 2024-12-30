package com.ecom2.notification_service.kafka.OrderConfirmation;

import com.ecom2.notification_service.kafka.order.Customer;
import com.ecom2.notification_service.kafka.order.Product;
import com.ecom2.notification_service.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {


}
