package com.mundim.ticketbackendspringboot.dto.response;
import com.mundim.ticketbackendspringboot.entity.Roles;
import lombok.*;


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
    private Roles roles;
}
