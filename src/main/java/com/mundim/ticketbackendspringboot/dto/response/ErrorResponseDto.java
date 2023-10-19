package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold Error response information"
)
public class ErrorResponseDto{
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
