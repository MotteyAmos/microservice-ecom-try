package com.ecom2.customer_service.customer.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> error
) {
}
