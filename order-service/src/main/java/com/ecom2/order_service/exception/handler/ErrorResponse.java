package com.ecom2.order_service.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> error
) {
}
