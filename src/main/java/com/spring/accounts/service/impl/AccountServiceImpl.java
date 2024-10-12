package com.spring.accounts.service.impl;

import com.spring.accounts.constants.AccountsConstants;
import com.spring.accounts.dto.AccountsDto;
import com.spring.accounts.dto.CustomerDto;
import com.spring.accounts.entity.Accounts;
import com.spring.accounts.entity.Customer;
import com.spring.accounts.exception.CustomerAlreadyExistsException;
import com.spring.accounts.exception.ResourceNotFoundException;
import com.spring.accounts.mapper.AccountsMapper;
import com.spring.accounts.mapper.CustomerMapper;
import com.spring.accounts.repository.AccountsRepository;
import com.spring.accounts.repository.CustomerRepository;
import com.spring.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDTO) {
        Customer customer= CustomerMapper.mapToCustomer(customerDTO,new Customer());
       if(customerRepository.findByMobileNumber(customerDTO.getMobileNumber()).isPresent())
           throw new CustomerAlreadyExistsException("Customer Already Registered with given mobile number: " + customerDTO.getMobileNumber());

        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber){
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        Accounts accounts=accountsRepository.getByCustomerId(customer.getCustomerId()).orElseThrow(()-> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString()));
        CustomerDto customerDTO=CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDTO.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDTO) {
        boolean isUpdated=false;

        AccountsDto accountsDTO=customerDTO.getAccountsDto();
        if(accountsDTO!=null) {
            Accounts account = accountsRepository.getByAccountNumber(accountsDTO.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString()));
            account=AccountsMapper.mapToAccounts(accountsDTO, account);
            accountsRepository.save(account);
            Long customerId= account.getCustomerId();

            Customer customer=customerRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerID", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepository.save(customer);

            isUpdated=true;

        }
    return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
