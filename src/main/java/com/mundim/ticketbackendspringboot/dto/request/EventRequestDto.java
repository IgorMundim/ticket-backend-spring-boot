package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Event",
        description = "Schema to hold Event information"
)
public class EventRequestDto {
    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    private String name;

    @NotNull(message = "Is active must not be null")
    private Boolean isActive;

    @NotNull(message = "Is virtual must not be null")
    private Boolean isVirtual;

    @NotNull(message = "Is Published must not be null")
    private Boolean isPublished;

    @NotNull(message = "Date End must not be null")
    private LocalDateTime dateEnd;

    @NotNull(message = "Date Start must not be null")
    private LocalDateTime dateStart;

    @NotBlank(message = "Name must not be null")
    private String description;

    @NotBlank(message = "Video URL must not be null")
    private String videoUrl;

}
