package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeasingResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private int storePrice;
    private int salePrice;
    private int studentPrice;
    private int units_solid;
    private int units;
}
