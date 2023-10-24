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
        name = "AccountResponse",
        description = "Schema to hold Account response information"
)
public class AccountResponseDto extends RepresentationModel<AccountResponseDto> {
    private Long id;
    private String username;
    private String email;
    private String profileImage;
    private PermissionResponseDto permission;
    private AddressResponseDto address;

}
