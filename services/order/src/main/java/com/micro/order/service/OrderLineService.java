package com.micro.order.service;

import com.micro.order.mapper.OrderLineMapper;
import com.micro.order.model.OrderLineRequest;
import com.micro.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var orderLine = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(orderLine).getId();
    }
}
