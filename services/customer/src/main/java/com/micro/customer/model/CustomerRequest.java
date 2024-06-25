package com.micro.customer.model;

public record CustomerRequest(
         Long id,
         String firstName,
         String lastName,
         String email,
         Address address
) {
}
