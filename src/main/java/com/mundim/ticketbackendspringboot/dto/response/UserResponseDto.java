package com.mundim.ticketbackendspringboot.dto.response;
import lombok.*;

import java.security.Permission;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String profileImage;
    private Permission permission;
}
