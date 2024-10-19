package com.micro.order.service;

import com.micro.order.exception.BusinessException;
import com.micro.order.kafka.OrderConfirmation;
import com.micro.order.kafka.OrderProducer;
import com.micro.order.mapper.OrderMapper;
import com.micro.order.model.OrderLineRequest;
import com.micro.order.model.OrderRequest;
import com.micro.order.model.customer.CustomerClient;
import com.micro.order.model.product.ProductClient;
import com.micro.order.model.product.PurchaseRequest;
import com.micro.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Long createOrder(OrderRequest orderRequest){
        var customerId = orderRequest.customerId();
       var customer = customerClient
                .findCustomerById(customerId).orElseThrow(() -> new BusinessException(
                        String.format("Cannot create order :: no Customer found with this provided id :: %s", customerId)));

        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

       for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            orderRequest.id(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()

                    )
            );
        }
        //todo start payment process
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
       return order.getId();
    }
}
