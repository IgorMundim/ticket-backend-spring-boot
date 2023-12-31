package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Login",
        description = "Schema to hold Login information"
)
public class LoginRequestDto {
    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String pwd;
}
