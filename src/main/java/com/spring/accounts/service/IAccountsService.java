package com.spring.accounts.service;

import com.spring.accounts.dto.CustomerDTO;

public interface IAccountsService {

    /**
     *
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO);
}
