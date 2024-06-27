package com.micro.customer.service;

import com.micro.customer.mapper.CustomerMapper;
import com.micro.customer.model.ApiResponse;
import com.micro.customer.model.CustomerRequest;
import com.micro.customer.model.CustomerResponse;
import com.micro.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        var customerResponse = customerMapper.toCustomerResponse(customer);
        ApiResponse<CustomerResponse> response = new ApiResponse<>(customerResponse,
                "create Customer was successful",
                HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
