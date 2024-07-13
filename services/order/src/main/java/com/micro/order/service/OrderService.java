package com.micro.order.service;

import com.micro.order.exception.BusinessException;
import com.micro.order.model.ApiResponse;
import com.micro.order.model.OrderRequest;
import com.micro.order.model.customer.CustomerClient;
import com.micro.order.model.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public ResponseEntity<ApiResponse<Long>> createOrder(OrderRequest orderRequest){
        var customerId = orderRequest.customerId();
        customerClient
                .findById(customerId).orElseThrow(() -> new BusinessException(
                        String.format("Cannot create order :: no Customer found with this provided id :: %s", customerId)));

        ApiResponse<Long> apiResponse = new ApiResponse<>(
                customerId,
                "find customer by ID was successful",
                HttpStatus.OK.value());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
