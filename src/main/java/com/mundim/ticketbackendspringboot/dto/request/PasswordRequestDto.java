package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Password",
        description = "Schema to hold Password information"
)
public class PasswordRequestDto {

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String newPwd;

    @NotBlank(message="New Password must not be blank")
    @Size(min=5, message="New Password must be at least 5 characters long")
    private String oldPwd;

}
