package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Category",
        description = "Schema to hold Category information"
)
public class CategoryRequestDto {
    private Long id;

    @NotBlank(message = "Name must not be null")
    @Size(min=2, max = 100, message = "Name must not be less than 2 and more than 100")
    private String name;

    @NotNull(message = "IsActive must not be null")
    private Boolean isActive;

    @NotBlank(message = "Name must not be null")
    private String url;

    @NotBlank(message = "Alt text must not be null")
    @Size(min=2, max = 100, message = "Alt text must not be less than 2 and more than 100")
    private String altText;

}
