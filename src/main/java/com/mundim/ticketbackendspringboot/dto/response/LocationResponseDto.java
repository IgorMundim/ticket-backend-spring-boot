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
        name = "LocationResponse",
        description = "Schema to hold Location response information"
)
public class LocationResponseDto extends RepresentationModel<LocationResponseDto> {
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
