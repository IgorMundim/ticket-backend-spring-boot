package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Location",
        description = "Schema to hold Location information"
)
public class LocationRequestDto {
    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    private String name;

    private String description;

    @NotNull(message = "Is active must not be null")
    private Boolean isActive;

    @NotNull(message = "Store price must not be null")
    @DecimalMin("0.0")
    private Long storePrice;

    @NotNull(message = "Sale price must not be null")
    @DecimalMin("5.0")
    private Long salePrice;

    @NotNull(message = "Student price must not be null")
    @DecimalMin("5.0")
    private Long studentPrice;

    @NotNull(message = "Units solid must not be null")
    private int unitsSolid;

    @NotNull(message = "Units must not be null")
    private int units;
}
