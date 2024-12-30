package com.ecom2.order_service.kafka;

import com.ecom2.order_service.customer.CustomerResponse;
import com.ecom2.order_service.order.PaymentMethod;
import com.ecom2.order_service.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
