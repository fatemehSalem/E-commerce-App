package com.micro.product.model;

import java.util.Map;

public record ErrorResponse(
        Map<String , String> errors
) {
}
