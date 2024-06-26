package com.micro.customer.controller;

import com.micro.customer.model.ApiResponse;
import com.micro.customer.model.CustomerRequest;
import com.micro.customer.model.CustomerResponse;
import com.micro.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        return  customerService.createCustomer(customerRequest);
    }
}
