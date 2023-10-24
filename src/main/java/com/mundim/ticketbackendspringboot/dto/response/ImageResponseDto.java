package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "ImageResponse",
        description = "Schema to hold Image response information"
)
public class ImageResponseDto extends RepresentationModel<ImageResponseDto> {
    private Long id;
    private String url;
    private String alt_text;
}
