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
        name = "PermissionResponse",
        description = "Schema to hold Permission response information"
)
public class PermissionResponseDto extends RepresentationModel<PermissionResponseDto> {
    private int id;
    private String roleName;
}
