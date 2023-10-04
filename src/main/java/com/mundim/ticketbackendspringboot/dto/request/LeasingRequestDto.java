package com.mundim.ticketbackendspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeasingRequestDto {
    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    private String name;

    private String description;

    @NotBlank(message = "Is active must not be null")
    private Boolean isActive;

    @NotBlank(message = "Store price must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    private int storePrice;

    @NotBlank(message = "Sale price must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    private int salePrice;

    @NotBlank(message = "Student price must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    private int studentPrice;

    @NotBlank(message = "Units solid must not be null")
    private int units_solid;

    @NotBlank(message = "Units must not be null")
    private int units;

    @NotBlank(message = "Event id must not be null")
    private Long event_id;
}
