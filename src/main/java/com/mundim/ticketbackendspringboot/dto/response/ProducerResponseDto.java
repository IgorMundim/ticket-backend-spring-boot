package com.mundim.ticketbackendspringboot.dto.response;

import com.mundim.ticketbackendspringboot.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "ProducerResponse",
        description = "Schema to hold Producer response information"
)
public class ProducerResponseDto extends BaseDto{
    private Long id;
    private String mobileNumber;
    private String email;
    private String confirmEmail;
    private String pwd;
    private String profileImage;
    private Address address;
}
