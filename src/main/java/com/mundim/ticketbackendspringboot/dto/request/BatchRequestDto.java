package com.mundim.ticketbackendspringboot.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BatchRequestDto {
    @NotNull(message = "Percentage must not be null")
    @DecimalMax("100.0") @DecimalMin("5.0")
    private Double percentage;

    @Future(message = "Batch stop must be an instant, date or time in the future")
    private LocalDateTime batchStopDate;

    @NotNull(message = "Sales quantity must not be null")
    private int salesQtd;

    private String description;

    @NotNull(message = "Is active must not be null")
    private Boolean isActive;
}
