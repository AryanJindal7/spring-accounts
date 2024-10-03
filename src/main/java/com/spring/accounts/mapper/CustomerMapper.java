package com.spring.accounts.mapper;

import com.spring.accounts.dto.CustomerDTO;
import com.spring.accounts.entity.Customer;

public class CustomerMapper {

        public static CustomerDTO mapToCustomerDto(Customer customer, CustomerDTO customerDto) {
            customerDto.setName(customer.getName());
            customerDto.setEmail(customer.getEmail());
            customerDto.setMobileNumber(customer.getMobileNumber());
            return customerDto;
        }

        public static Customer mapToCustomer(CustomerDTO customerDto, Customer customer) {
            customer.setName(customerDto.getName());
            customer.setEmail(customerDto.getEmail());
            customer.setMobileNumber(customerDto.getMobileNumber());
            return customer;
        }

    }

