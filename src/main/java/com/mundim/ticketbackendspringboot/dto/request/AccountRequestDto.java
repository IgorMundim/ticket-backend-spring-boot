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
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountRequestDto {
    private Long id;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    private String pwd;

    private String profileImage;

}
