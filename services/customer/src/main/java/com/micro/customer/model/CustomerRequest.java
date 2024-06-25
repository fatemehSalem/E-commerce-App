package com.micro.customer.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         Long id,

         @NotNull(message = "customer firstName is required")
         String firstName,

         @NotNull(message = "customer lastName is required")
         String lastName,

         @NotNull(message = "customer email is required")
         @Email(message = "customer email is not valid email address")
         String email,

         Address address
) {
}
