package com.micro.order.mapper;

import com.micro.order.model.Order;
import com.micro.order.model.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest){
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();

    }
}
