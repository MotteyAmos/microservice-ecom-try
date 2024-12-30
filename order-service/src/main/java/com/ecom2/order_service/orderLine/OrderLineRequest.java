package com.ecom2.order_service.orderLine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest
        (Integer id,
         Integer orderId,
         Integer productId,
         Integer quantity) {
}
