package com.spring.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(name = "Error Response Schema",description = "Schema to holds Error details")

public class ErrorResponseDto {
    @Schema(description = "It includes API PATH",example = "/api/v1/account")
    private String apiPath;
    @Schema(description = "It contains error code",example = "500")
    private HttpStatus errorCode;
    @Schema(description = "It includes the error message",example = "Internal server error")
    private String errorMessage;
    private LocalDateTime errorTime;
}
