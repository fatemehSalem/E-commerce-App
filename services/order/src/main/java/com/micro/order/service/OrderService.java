package com.micro.order.service;

import com.micro.order.model.ApiResponse;
import com.micro.order.model.OrderRequest;
import com.micro.order.model.customer.CustomerClient;
import com.micro.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;

    public ResponseEntity<ApiResponse<Long>> createOrder(OrderRequest orderRequest){
        return null;
    }
}
