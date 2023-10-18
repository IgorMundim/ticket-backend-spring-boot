package com.mundim.ticketbackendspringboot.dto.response;
import lombok.*;

import java.security.Permission;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponseDto {
    private Long id;
    private String username;
    private String email;
    private String profileImage;
    private Permission permission;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
