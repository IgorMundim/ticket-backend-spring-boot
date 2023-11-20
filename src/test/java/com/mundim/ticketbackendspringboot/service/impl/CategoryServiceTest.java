package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.entity.Category;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockCategory;
import com.mundim.ticketbackendspringboot.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {
    MockCategory input;
    @InjectMocks
    CategoryService service;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockCategory();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("It should not find 'Category' with invalid id when trying get the category.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetCategoryById(){
        Category entity = input.mockEntity(1);
        CategoryRequestDto entityDto = input.mockDto(1);
        when(categoryRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetch(entity.getId()); });
        String expectedMessage = "Category not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not find 'Category' with invalid id when trying update the category.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdateCategoryById(){
        Category entity = input.mockEntity(1);
        CategoryRequestDto entityDto = input.mockDto(1);
        when(categoryRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Category not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should not find 'Category' with invalid id when trying delete the category.")
    void testWillThrowResourceNotFoundExceptionWhenTryDeleteCategoryById(){
        Category entity = input.mockEntity(1);
        when(categoryRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.delete( 1L); });
        String expectedMessage = "Category not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should create a Category")
    void testWillCreateEvent(){
        Category entity = input.mockEntity(1);
        CategoryRequestDto entityDto = input.mockDto(1);

        CategoryResponseDto result = service.create(entityDto);
        assertNotNull(result);
        assertEquals(CategoryResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/category/1>;rel=\"self\""));
        assertEquals("NameTest1", result.getName());
        assertEquals("UrlTest1", result.getUrl());
        assertEquals("AltTextTest1", result.getAltText());
        assertEquals(true, result.getIsActive());
    }

    @Test
    @DisplayName("It should get an 'Category' by account id")
    void testFetchCategoryById(){
        Category entity = input.mockEntity(1);
        when(categoryRepository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));
        var result = service.fetch(entity.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(CategoryResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/category/1>;rel=\"self\""));
        assertEquals("NameTest1", result.getName());
        assertEquals("UrlTest1", result.getUrl());
        assertEquals("AltTextTest1", result.getAltText());
        assertEquals(true, result.getIsActive());
    }
    @Test
    @DisplayName("It should update a 'Category'")
    void testWillUpdateCategory(){
        Category oldEntity = input.mockEntity(1);

        CategoryRequestDto entityDto = input.mockDto(2);
        when(categoryRepository.findById(oldEntity.getId())).thenReturn(
                Optional.of(oldEntity)
        );

        CategoryResponseDto result = service.update(entityDto, 1L);
        assertNotNull(result);
        assertEquals(CategoryResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/category/1>;rel=\"self\""));
        assertEquals("NameTest2", result.getName());
        assertEquals("UrlTest2", result.getUrl());
        assertEquals("AltTextTest2", result.getAltText());
        assertEquals(true, result.getIsActive());
    }
    @Test
    @DisplayName("It should get all 'Category'")
    void testFetchAllCategory(){
        List<Category> entities = input.mockEntityList();
        when(categoryRepository.findAll())
                .thenReturn(entities);
        List<CategoryResponseDto> result = service.fetchAll();
        CategoryResponseDto firstCategory = result.get(1);
        assertNotNull(result);
        assertEquals(CategoryResponseDto.class, firstCategory.getClass());
        assertTrue(firstCategory.getLinks().toString().contains("</api/v1/category/1>;rel=\"self\""));
        assertEquals("NameTest1", firstCategory.getName());
        assertEquals("UrlTest1", firstCategory.getUrl());
        assertEquals("AltTextTest1", firstCategory.getAltText());
        assertEquals(true, firstCategory.getIsActive());
    }

    @Test
    @DisplayName("It should get all 'Event Category'")
    void testFetchAllCategoryByEventId(){
        List<Category> entities = input.mockEntityList();
        when(categoryRepository.findByEventsId(1L))
                .thenReturn(entities);
        List<CategoryResponseDto> result = service.fetchAllByEventId(1L);
        CategoryResponseDto firstCategory = result.get(1);
        assertNotNull(result);
        assertEquals(CategoryResponseDto.class, firstCategory.getClass());
        assertTrue(firstCategory.getLinks().toString().contains("</api/v1/event/1/category>;rel=\"self\",</api/v1/event/1>;rel=\"Event\""));
        assertEquals("NameTest1", firstCategory.getName());
        assertEquals("UrlTest1", firstCategory.getUrl());
        assertEquals("AltTextTest1", firstCategory.getAltText());
        assertEquals(true, firstCategory.getIsActive());
    }

    @Test
    @DisplayName("It should delete a 'Category'")
    void testDeleteCategory(){
        Category entity = input.mockEntity(1);
        when(categoryRepository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));
        service.delete(1L);
    }
}