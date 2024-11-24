package com.spring.accounts.controller;

import com.spring.accounts.constants.AccountsConstants;
import com.spring.accounts.dto.AccountContactInfoDTO;
import com.spring.accounts.dto.CustomerDto;
import com.spring.accounts.dto.ErrorResponseDto;
import com.spring.accounts.dto.ResponseDto;
import com.spring.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "CRUD API for account management", description = "CRUD API's to create, fetch , update and delete the records")
public class AccountController {

    @Autowired
    private IAccountsService iAccountsService;
    @Value("${build.version}")
    private String buildVersion;
    @Autowired
    private Environment environment;
    @Autowired
    private AccountContactInfoDTO accountContactInfoDTO;

    @PostMapping("/create")
    @Operation(summary = "Create Account Rest API",description = "Create Customers and Bank Account")
    @ApiResponse(responseCode = "201",description = "HTTP Status - CREATED")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDTO){
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetchCustomer")
    @Operation(summary = "Fetch Account Rest API",description = "Fetch Customers and Bank Account using mobile number")
    @ApiResponse(responseCode = "200",description = "HTTP Status - OK")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam     @Pattern(regexp = "(^[0-9]{10})",message = "Mobile Number must be 10 digits")
                                                               String mobileNumber){
        return ResponseEntity.status(HttpStatus.OK).body( iAccountsService.fetchAccount(mobileNumber));
    }

    @PutMapping("/update")
    @Operation(summary = "Update Account Details Rest API",description = "Update Customers and Bank Account")
    @ApiResponse(responseCode = "200",description = "HTTP Status - OK",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @ApiResponse(responseCode = "417",description = "Exception Failed")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDTO){
        boolean isUpdated=iAccountsService.updateAccount(customerDTO);

        if(isUpdated)
        {
            return ResponseEntity.ok(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }

    }

    @DeleteMapping("/delete")
    @Operation(summary = "Fetch Account Rest API",description = "Delete Customers and Bank Account using mobile number")
    @ApiResponse(responseCode = "200",description = "HTTP Status - OK",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam     @Pattern(regexp = "(^[0-9]{10})",message = "Mobile Number must be 10 digits")
                                                                String mobileNumber){
        boolean isUpdated=iAccountsService.deleteAccount(mobileNumber);

        if(isUpdated)
        {
            return ResponseEntity.ok(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }

    }

    @Operation(summary = "Fetch Account Rest API Build version",description = "Fetch Build details")
    @ApiResponse(responseCode = "200",description = "HTTP Status - OK")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.ok(buildVersion);
    }

    @Operation(summary = "Fetch Account Rest API Java version",description = "Fetch Java version details")
    @ApiResponse(responseCode = "200",description = "HTTP Status - OK")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok(environment.getProperty("MAVEN_HOME"));
    }

    @Operation(summary = "Fetch Account Rest API Contact Info",description = "Fetch contact info details")
    @ApiResponse(responseCode = "200",description = "HTTP Status - OK")
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDTO> getContactInfo(){
        return ResponseEntity.ok(accountContactInfoDTO);
    }
}
