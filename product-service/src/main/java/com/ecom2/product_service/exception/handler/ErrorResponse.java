package com.ecom2.product_service.exception.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> error
) {
}
