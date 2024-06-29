package com.micro.customer.controller;

import com.micro.customer.model.ApiResponse;
import com.micro.customer.model.CustomerRequest;
import com.micro.customer.model.CustomerResponse;
import com.micro.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public ResponseEntity<ApiResponse<Long>> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest){
        return  customerService.updateCustomer(customerRequest);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/exits/{customerId}")
    public ResponseEntity<ApiResponse<Boolean>> customerExitsById(@PathVariable("customerId") Long customerId){
        return customerService.customerExitsById(customerId);
    }

}
