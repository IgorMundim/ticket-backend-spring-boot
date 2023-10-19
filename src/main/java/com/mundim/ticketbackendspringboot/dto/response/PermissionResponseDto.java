package com.mundim.ticketbackendspringboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "PermissionResponse",
        description = "Schema to hold Permission response information"
)
public class PermissionResponseDto extends BaseDto{
    private Long id;
    private String roleName;
}
