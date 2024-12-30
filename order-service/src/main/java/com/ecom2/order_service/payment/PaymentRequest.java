package com.ecom2.order_service.payment;

import com.ecom2.order_service.customer.CustomerResponse;
import com.ecom2.order_service.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
