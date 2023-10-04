package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressResponseDto {
    private Long id;
    private String zipcode;
    private String complement;
    private String city;
    private String neighborhood;
    private String number;
    private String street;
    private String uf;
}
