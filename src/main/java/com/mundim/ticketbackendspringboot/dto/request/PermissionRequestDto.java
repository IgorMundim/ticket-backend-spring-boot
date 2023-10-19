package com.mundim.ticketbackendspringboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "Permission",
        description = "Schema to hold Permission information"
)
public class PermissionRequestDto {
    @NotBlank(message = "Role name must not be blank")
    private String roleName;
}
