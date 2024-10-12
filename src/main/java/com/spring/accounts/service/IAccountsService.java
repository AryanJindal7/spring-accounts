package com.spring.accounts.service;

import com.spring.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDTO
     */
    void createAccount(CustomerDto customerDTO);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDTO);

    boolean deleteAccount(String mobileNumber);
}
