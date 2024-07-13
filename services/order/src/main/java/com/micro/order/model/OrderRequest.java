package com.micro.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,

        String reference,

        @Positive(message = "order amount should be positive")
        BigDecimal amount,

        @NotNull(message = "payment Method should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "customer should be present")
        @NotEmpty(message = "customer should be present")
        @NotBlank(message = "customer should be present")
//        Use @NotNull for fields that must be assigned but can be empty.
//        Use @NotEmpty for fields that require some content (not empty) for any supported type.
//        Use @NotBlank for string fields that specifically cannot be blank (no whitespace).
        Long customerId,

        @NotEmpty(message = "you should purchase at least one product")
        List<PurchaseRequest> products
) {
}
