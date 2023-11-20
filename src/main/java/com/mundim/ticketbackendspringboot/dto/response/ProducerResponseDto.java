package com.mundim.ticketbackendspringboot.dto.response;

import com.mundim.ticketbackendspringboot.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "ProducerResponse",
        description = "Schema to hold Producer response information"
)
public class ProducerResponseDto extends RepresentationModel<ProducerResponseDto> {
    private Long id;
    private String mobileNumber;
    private String email;
    private String confirmEmail;
    private String pwd;
    private String profileImage;
    private Address address;
}
