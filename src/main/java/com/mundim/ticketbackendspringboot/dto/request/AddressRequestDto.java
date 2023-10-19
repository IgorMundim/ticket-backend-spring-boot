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
        name = "Address",
        description = "Schema to hold Address information"
)
public class AddressRequestDto {

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Mobile number must be 12 digits")
    private String mobileNumber;

    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{8})", message = "Zip code must be 5 digits")
    private String zipcode;

    private String complement;

    @NotBlank(message = "City must not be blank")
    @Size(min = 2, max = 100, message = "City must be more than 2 characters and less 100")
    private String city;

    @Size(min = 2, max = 100, message = "Neighborhood must be more than 2 characters and less 100")
    private String neighborhood;

    @Size(min = 2, max = 100, message = "Number must be more than 2 characters and less 100")
    private String number;

    @Size(min = 2, max = 100, message = "Street must be more than 2 characters and less 100")
    private String street;

    @Pattern(regexp = "(^(?i)(\\s*(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)?)$)", message = "UF must be 2 characters")
    private String uf;
}
