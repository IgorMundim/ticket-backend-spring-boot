package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponseDto {
    private Long id;
    private String name;
    private Boolean isActive;
    private String url;
    private String altText;
}
