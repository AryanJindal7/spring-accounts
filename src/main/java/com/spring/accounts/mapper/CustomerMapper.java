package com.spring.accounts.mapper;

import com.spring.accounts.dto.CustomerDto;
import com.spring.accounts.entity.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {

        public CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
            customerDto.setName(customer.getName());
            customerDto.setEmail(customer.getEmail());
            customerDto.setMobileNumber(customer.getMobileNumber());
            return customerDto;
        }

        public Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setMobileNumber(customerDto.getMobileNumber());
            return customer;
        }

    }

