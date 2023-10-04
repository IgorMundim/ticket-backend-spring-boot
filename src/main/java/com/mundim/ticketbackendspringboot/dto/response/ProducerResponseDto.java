package com.mundim.ticketbackendspringboot.dto.response;

import com.mundim.ticketbackendspringboot.entity.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProducerResponseDto {
    private Long id;
    private String mobileNumber;
    private String email;
    private String confirmEmail;
    private String pwd;
    private String profileImage;
    private Address address;
}
