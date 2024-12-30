package com.ecom2.payment_service.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "FirstName is required")
        String firstName,
        @NotNull(message = "Lastname is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "The customer si not correctly formatted")
        String email
) {
}
