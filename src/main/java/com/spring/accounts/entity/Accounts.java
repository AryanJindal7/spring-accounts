package com.spring.accounts.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Accounts extends BaseEntity{
    @Column
    private Long customerId;
    @Column
    @Id
    private Long accountNumber;
    @Column
    private String accountType;
    @Column
    private String branchAddress;
}
