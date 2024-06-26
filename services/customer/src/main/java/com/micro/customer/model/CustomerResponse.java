package com.micro.customer.model;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
