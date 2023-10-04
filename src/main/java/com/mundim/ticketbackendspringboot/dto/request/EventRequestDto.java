package com.mundim.ticketbackendspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventRequestDto {
    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    private String name;

    @NotBlank(message = "Is active must not be null")
    private Boolean isActive;

    @NotBlank(message = "Is virtual must not be null")
    private Boolean isVirtual;

    @NotBlank(message = "Is Published must not be null")
    private Boolean isPublished;

    @NotBlank(message = "Date End must not be null")
    private LocalDateTime dateEnd;

    @NotBlank(message = "Date Start must not be null")
    private String date_start;

    @NotBlank(message = "Name must not be null")
    private String description;

    @NotBlank(message = "Video URL must not be null")
    private String videoUrl;

}
