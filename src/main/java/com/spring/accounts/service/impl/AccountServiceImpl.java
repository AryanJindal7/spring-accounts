package com.spring.accounts.service.impl;

import com.spring.accounts.constants.AccountsConstants;
import com.spring.accounts.dto.CustomerDTO;
import com.spring.accounts.entity.Accounts;
import com.spring.accounts.entity.Customer;
import com.spring.accounts.exception.CustomerAlreadyExistsException;
import com.spring.accounts.mapper.CustomerMapper;
import com.spring.accounts.repository.AccountsRepository;
import com.spring.accounts.repository.CustomerRepository;
import com.spring.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDTO customerDTO) {

        Customer customer= CustomerMapper.mapToCustomer(customerDTO,new Customer());

       if(customerRepository.findByMobileNumber(customerDTO.getMobileNumber()).isPresent())
           throw new CustomerAlreadyExistsException("Customer Already Registered with given mobile number: " + customerDTO.getMobileNumber());

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Aryan");
        Customer savedCustomer=customerRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("");

        return newAccount;
    }
}
