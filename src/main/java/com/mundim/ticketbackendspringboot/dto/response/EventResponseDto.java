package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventResponseDto {
    private Long id;
    private String name;
    private Boolean isActive;
    private Boolean isVirtual;
    private Boolean isPublished;
    private LocalDateTime dateEnd;
    private String dateStart;
    private String description;
    private String videoUrl;

}
