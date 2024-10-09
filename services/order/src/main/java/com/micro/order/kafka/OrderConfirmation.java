package com.micro.order.kafka;

import com.micro.order.model.PaymentMethod;
import com.micro.order.model.customer.CustomerResponse;
import com.micro.order.model.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
