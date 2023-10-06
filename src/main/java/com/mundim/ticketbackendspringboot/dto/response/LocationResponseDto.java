package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private Long storePrice;
    private Long salePrice;
    private Long studentPrice;
    private int unitsSolid;
    private int units;
}
