package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "BatchResponse",
        description = "Schema to hold Batch response information"
)
public class BatchResponseDto extends RepresentationModel<BatchResponseDto> {
    private Long id;
    private Double percentage;
    private LocalDateTime batchStopDate;
    private int salesQtd;
    private String description;
    private Boolean isActive;
}
