package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.entity.Category;
import org.modelmapper.ModelMapper;

public class CategoryMapper {
    public static Category toCategory(CategoryRequestDto categoryDto){
        return new ModelMapper().map(categoryDto, Category.class);
    }

    public static CategoryResponseDto toDto(Category category){
        return  new ModelMapper().map(category, CategoryResponseDto.class);
    }
}
