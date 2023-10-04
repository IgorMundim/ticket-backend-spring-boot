package com.mundim.ticketbackendspringboot.dto.response;
import lombok.*;

import java.security.Permission;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponseDto {
    private Long id;
    private String mobileNumber;
    private String email;
    private String pwd;
    private String profileImage;
    private Permission permission;
}
