package com.micro.order.service;

import com.micro.order.exception.BusinessException;
import com.micro.order.mapper.OrderMapper;
import com.micro.order.model.ApiResponse;
import com.micro.order.model.OrderLineRequest;
import com.micro.order.model.OrderRequest;
import com.micro.order.model.customer.CustomerClient;
import com.micro.order.model.product.ProductClient;
import com.micro.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public ResponseEntity<ApiResponse<Long>> createOrder(OrderRequest orderRequest){
        var customerId = orderRequest.customerId();
        customerClient
                .findById(customerId).orElseThrow(() -> new BusinessException(
                        String.format("Cannot create order :: no Customer found with this provided id :: %s", customerId)));

        productClient.purchaseProducts(orderRequest.products());

        orderRepository.save(orderMapper.toOrder(orderRequest));

/*        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            orderRequest.id(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()

                    )
            );
        }*/
        //Stream API

        orderRequest.products().stream()
                .map(purchaseRequest -> new OrderLineRequest(
                        null,
                        orderRequest.id(),
                        purchaseRequest.productId(),
                        purchaseRequest.quantity()

                )).forEach(orderLineService:: saveOrderLine);

        ApiResponse<Long> apiResponse = new ApiResponse<>(
                customerId,
                "create order was successful",
                HttpStatus.OK.value());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
