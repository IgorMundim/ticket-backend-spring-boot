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
        name = "CategoryResponse",
        description = "Schema to hold Category response information"
)
public class CategoryResponseDto extends RepresentationModel<CategoryResponseDto> {
    private Long id;
    private String name;
    private Boolean isActive;
    private String url;
    private String altText;
}
