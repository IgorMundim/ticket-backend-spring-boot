package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Producer",
        description = "Schema to hold Producer information"
)
public class ProducerRequestDto {
    @NotBlank(message = "Company Name must not be null")
    @Size(min=2, max = 100, message = "Company Name must not be less than 2 and more than 100")
    private String companyName;

    @NotBlank(message = "CNPJ must not be null")
    @Pattern(regexp = "(^$|[0-9]{14})", message = "CNPJ must be 14 numbers")
    private int cnpj;

    @NotBlank(message = "Fantasy Name must not be null")
    @Size(min=2, max = 100, message = "Fantasy Name must not be less than 2 and more than 100")
    private String fantasyName;

    @NotBlank(message = "State Registration must not be null")
    @Pattern(regexp = "(^$|[0-9]{20})", message = "State Registration must be 14 numbers")
    private String stateRegistration;

    @NotBlank(message = "Municipal Registration must not be null")
    @Size(min=2, max = 100, message = "Municipal Registration must not be less than 2 and more than 100")
    private String municipalRegistration;
}
