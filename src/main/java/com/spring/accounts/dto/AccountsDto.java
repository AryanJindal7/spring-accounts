package com.spring.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account Schema",description = "Schema to holds account information")

public class AccountsDto {
    @Schema(name = "Account Number",example = "1234567890")
    @NotEmpty(message = "Account number can't be null or empty")
    @Pattern(regexp = "(^[0-9]{10})",message = "Account Number must be 10 digits")
    private Long accountNumber;
    @Schema(name = "Account Type",example = "Savings")
    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @Schema(name = "Branch Address")
    @NotEmpty(message = "Branch Address can't be null or empty")
    private String branchAddress;
}
