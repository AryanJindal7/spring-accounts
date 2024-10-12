package com.spring.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response",description = "Schema to hold Status code and status message")
public class ResponseDto {

    @Schema(description = "Schema to hold status code")
    private String statusCode;
    @Schema(description = "Schema to hold status message")
    private String statusMessage;
}
