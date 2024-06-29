package com.micro.customer.mapper;

import com.micro.customer.model.Customer;
import com.micro.customer.model.CustomerRequest;
import com.micro.customer.model.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest){
        return Customer
                .builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .address(customerRequest.address())
                .email(customerRequest.email())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer)
    {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getFirstName(),
                customer.getEmail()
        );

    }
}
