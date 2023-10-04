package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.ImageRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ImageResponseDto;
import com.mundim.ticketbackendspringboot.entity.Image;
import org.modelmapper.ModelMapper;

public class ImageMapper {
    public static Image toImage(ImageRequestDto imageDto){
        return new ModelMapper().map(imageDto, Image.class);
    }

    public static ImageResponseDto toDto(Image image){
        return  new ModelMapper().map(image, ImageResponseDto.class);
    }
}
