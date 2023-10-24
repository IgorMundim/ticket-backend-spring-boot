package com.mundim.ticketbackendspringboot.dto.response;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseDto {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
