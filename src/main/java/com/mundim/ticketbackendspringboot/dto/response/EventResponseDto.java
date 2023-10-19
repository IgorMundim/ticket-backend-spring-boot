package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "EventResponse",
        description = "Schema to hold Event response information"
)
public class EventResponseDto extends BaseDto{
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
