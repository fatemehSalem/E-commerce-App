package com.micro.order.mapper;

import com.micro.order.model.Order;
import com.micro.order.model.OrderLine;
import com.micro.order.model.OrderLineRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest){
        return OrderLine.builder().
                id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();

    }
}
