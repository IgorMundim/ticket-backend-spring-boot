package com.mundim.ticketbackendspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BatchRequestDto {
    @NotBlank(message = "Percentage must not be null")
    @Pattern(regexp = "(^[0-9]+(\\.[0-9]{1,2})?$)")
    private int percentage;

    @NotBlank(message = "Batch stop date must not be null")
    private LocalDateTime batchStopDate;

    @NotBlank(message = "Sales quantity must not be null")
    private int salesQtd;

    private String description;

    @NotBlank(message = "Is active must not be null")
    private Boolean isActive;

    @NotBlank(message = "Event id must not be null")
    @Pattern(regexp = "(^\\d+$)")
    private Long event_id;
}
