package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "AddressResponse",
        description = "Schema to hold Address response information"
)
public class AddressResponseDto extends RepresentationModel<AddressResponseDto> {
    private Long id;
    private String mobileNumber;
    private String zipcode;
    private String complement;
    private String city;
    private String neighborhood;
    private String number;
    private String street;
    private String uf;
}
