package com.micro.customer.service;

import com.ctc.wstx.util.StringUtil;
import com.micro.customer.exception.CustomerNotFoundException;
import com.micro.customer.mapper.CustomerMapper;
import com.micro.customer.model.ApiResponse;
import com.micro.customer.model.Customer;
import com.micro.customer.model.CustomerRequest;
import com.micro.customer.model.CustomerResponse;
import com.micro.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        var customerResponse = customerMapper.toCustomerResponse(customer);
        ApiResponse<CustomerResponse> response = new ApiResponse<>(
                customerResponse,
                "create Customer was successful",
                HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public ResponseEntity<ApiResponse<Long>> updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot update customer :: no Customer found with this provided id :: %s", customerRequest.id())
        ));
        mergeCustomer(customerRequest, customer);
        ApiResponse<Long> response = new ApiResponse<>(
                customer.getId(),
                "update Customer was successful",
                HttpStatus.OK.value());
        return null;
    }

    private void mergeCustomer(CustomerRequest customerRequest, Customer customer) {
        if (StringUtils.isNotBlank(customerRequest.firstName()))
            customer.setFirstName(customerRequest.firstName());

        if (StringUtils.isNotBlank(customerRequest.lastName()))
            customer.setLastName(customerRequest.lastName());

        if (StringUtils.isNotBlank(customerRequest.email()))
            customer.setEmail(customerRequest.email());

        if (customerRequest.address() != null)
            customer.setAddress(customerRequest.address());
    }
}
