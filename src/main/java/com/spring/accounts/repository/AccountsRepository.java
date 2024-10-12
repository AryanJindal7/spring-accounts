package com.spring.accounts.repository;

import com.spring.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts,String> {

    Optional<Accounts> getByCustomerId(Long customerId);

    Optional<Accounts> getByAccountNumber(Long accountNumber);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
