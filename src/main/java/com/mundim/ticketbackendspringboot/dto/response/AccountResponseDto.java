package com.mundim.ticketbackendspringboot.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
        name = "AccountResponse",
        description = "Schema to hold Account response information"
)
public class AccountResponseDto extends BaseDto{
    private Long id;
    private String username;
    private String email;
    private String profileImage;
    private PermissionResponseDto permission;
    private AddressResponseDto address;

}
