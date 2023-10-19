package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "LocationResponse",
        description = "Schema to hold Location response information"
)
public class LocationResponseDto extends BaseDto{
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
