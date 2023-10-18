package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.entity.Category;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.CategoryRepository;
import com.mundim.ticketbackendspringboot.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDto create(CategoryRequestDto categoryDto) {
        Category category = Mapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        return Mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto fetch(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", Long.toString(id))
        );
        return Mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto categoryDto, Long id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", Long.toString(id))
        );
        Category category = Mapper.map(categoryDto, Category.class);
        category.setId(id);
        categoryRepository.save(category);
        return Mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", Long.toString(id))
        );
        categoryRepository.deleteById(id);
    }
}
