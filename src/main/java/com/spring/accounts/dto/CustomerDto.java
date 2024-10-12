package com.spring.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer Schema",description = "Schema to holds customer and account information")
public class CustomerDto {
    @Schema(description = "Name of the customer",example = "Aryan")
    @NotEmpty(message = "Name can't be empty or null")
    @Size(min = 5,max = 30,message = "The length of name must be in between 5 and 30")
    private String name;

    @Schema(description = "Email of the customer",example = "Aryan@xyz.com")
    @NotEmpty(message = "Email can't be empty or null")
    @Email(message = "Email address must be a valid value")
    private String email;

    @Schema(description = "Mobile Number of the customer",example = "6666666666")
    @NotEmpty(message = "Mobile number can't be null or empty")
    @Pattern(regexp = "(^[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
