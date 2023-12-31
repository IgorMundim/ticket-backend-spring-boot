package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Image",
        description = "Schema to hold Image information"
)
public class ImageRequestDto {
    @NotNull(message = "Url must not be null")
    private MultipartFile url;

    @NotBlank(message = "Alt text must not be null")
    @Size(min=2, max = 100, message = "Alt text must not be less than 2 and more than 100")
    private String altText;

}
