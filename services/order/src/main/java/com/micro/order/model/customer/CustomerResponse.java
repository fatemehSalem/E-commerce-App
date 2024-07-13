package com.micro.order.model.customer;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}