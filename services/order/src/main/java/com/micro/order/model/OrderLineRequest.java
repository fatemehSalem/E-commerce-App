package com.micro.order.model;

public record OrderLineRequest(Long id,
                               Long orderId,
                               Long productId,
                               double quantity) {
}
