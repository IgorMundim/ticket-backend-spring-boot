package com.mundim.ticketbackendspringboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Producer extends BaseEntity {
    @Id
    private int producerId;

    @NotBlank(message = "Company Name must not be null")
    @Size(min=2, max = 100, message = "Company Name must not be less than 2 and more than 100")
    String companyName;

    @NotBlank(message = "CNPJ must not be null")
    @Pattern(regexp = "(^$|[0-9]{14})", message = "CNPJ must be 14 numbers")
    int cnpj;

    @NotBlank(message = "Fantasy Name must not be null")
    @Size(min=2, max = 100, message = "Fantasy Name must not be less than 2 and more than 100")
    String fantasyName;

    @NotBlank(message = "State Registration must not be null")
    @Pattern(regexp = "(^$|[0-9]{20})", message = "State Registration must be 14 numbers")
    String stateRegistration;

    @NotBlank(message = "Municipal Registration must not be null")
    @Size(min=2, max = 100, message = "Municipal Registration must not be less than 2 and more than 100")
    String municipalRegistration;
}
