package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BatchResponseDto {
    private Long id;
    private int percentage;
    private LocalDateTime batchStopDate;
    private int salesQtd;
    private String description;
    private Boolean isActive;
}
