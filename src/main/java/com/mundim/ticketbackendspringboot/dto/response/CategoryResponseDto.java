package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "CategoryResponse",
        description = "Schema to hold Category response information"
)
public class CategoryResponseDto extends BaseDto{
    private Long id;
    private String name;
    private Boolean isActive;
    private String url;
    private String altText;
}
