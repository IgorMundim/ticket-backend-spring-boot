package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class MockCategory {
    public Category mockEntity(){
        return mockEntity(0);
    }
    public CategoryRequestDto mockDto(){
        return mockDto(0);
    }

    public List<Category> mockEntityList(){
        List<Category> entities = new ArrayList<Category>();
        for(int i =0; i<14; i++){
            entities.add(mockEntity(i));
        }
        return entities;
    }

    public List<CategoryRequestDto> mockDtoList(){
        List<CategoryRequestDto> entities = new ArrayList<CategoryRequestDto>();
        for(int i =0; i<14; i++){
            entities.add(mockDto(i));
        }
        return entities;
    }
    public Category mockEntity(Integer number){
        Category entity = new Category();
        entity.setId(number.longValue());
        entity.setName("NameTest"+number);
        entity.setIsActive(true);
        entity.setUrl("UrlTest"+number);
        entity.setAltText("AltTextTest"+number);
        return entity;
    }

    public CategoryRequestDto mockDto(Integer number){
        CategoryRequestDto entity = new CategoryRequestDto();
        entity.setId(number.longValue());
        entity.setName("NameTest"+number);
        entity.setIsActive(true);
        entity.setUrl("UrlTest"+number);
        entity.setAltText("AltTextTest"+number);
        entity.setIsActive(true);
        return entity;
    }
}
