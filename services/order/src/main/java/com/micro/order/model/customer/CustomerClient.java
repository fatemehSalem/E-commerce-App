package com.micro.order.model.customer;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
@CircuitBreaker(name = "myServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
public interface CustomerClient {
    @GetMapping("/findById/{customerId}")
    Optional<Long> findById(@PathVariable("customerId") Long customerId);

    default String fallbackMethod(Long id, Throwable throwable) {
        return "Fallback response test";
    }
}
