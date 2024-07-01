package com.micro.customer.model;

import java.util.Map;

public record ErrorResponse(
        Map<String , String> errors
) {
}
