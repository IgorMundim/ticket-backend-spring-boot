package com.mundim.ticketbackendspringboot.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageResponseDto {
    private Long id;
    private String url;
    private String alt_text;
}
