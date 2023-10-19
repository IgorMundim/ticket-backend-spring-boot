package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "ImageResponse",
        description = "Schema to hold Image response information"
)
public class ImageResponseDto extends BaseDto{
    private Long id;
    private String url;
    private String alt_text;
}
