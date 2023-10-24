package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.controller.CategoryController;
import com.mundim.ticketbackendspringboot.controller.EventController;
import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.entity.Category;
import com.mundim.ticketbackendspringboot.exception.AlreadyExistsException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.CategoryRepository;
import com.mundim.ticketbackendspringboot.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto create(CategoryRequestDto categoryDto) {
        try {
            Category category = Mapper.map(categoryDto, Category.class);
            categoryRepository.save(category);
            return Mapper.map(category, CategoryResponseDto.class)
                    .add(linkTo(methodOn(CategoryController.class).getById(category.getId())).withSelfRel());
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new AlreadyExistsException(String.format("Category name %s already registered", categoryDto.getName()));
        }
    }

    @Override
    public CategoryResponseDto fetch(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", Long.toString(id))
        );
        return Mapper.map(category, CategoryResponseDto.class)
                .add(linkTo(methodOn(CategoryController.class).getById(id)).withSelfRel());
    }

    @Override
    public List<CategoryResponseDto> fetchAllByEventId(Long id) {
        List<CategoryResponseDto> categories = Mapper.mapList(categoryRepository.findByEventsId(id), CategoryResponseDto.class);
        categories.forEach(p -> Mapper.map(p, CategoryResponseDto.class)
                .add(linkTo(methodOn(CategoryController.class).getAllByEventId(id)).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(id)).withRel("Event")));

        return categories;
    }

    @Override
    public List<CategoryResponseDto> fetchAll() {
        List<CategoryResponseDto> categories = Mapper.mapList(categoryRepository.findAll(), CategoryResponseDto.class);
        categories.forEach(p -> Mapper.map(p, CategoryResponseDto.class)
                .add(linkTo(methodOn(CategoryController.class).getById(p.getId())).withSelfRel()));
        return categories;
    }
    @Override
    public CategoryResponseDto update(CategoryRequestDto categoryDto, Long id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", Long.toString(id))
        );
        Category category = Mapper.map(categoryDto, Category.class);
        category.setId(id);
        categoryRepository.save(category);
        return Mapper.map(category, CategoryResponseDto.class)
                .add(linkTo(methodOn(CategoryController.class).getById(id)).withSelfRel());
    }

    @Override
    public void delete(Long id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", Long.toString(id))
        );
        categoryRepository.deleteById(id);
    }
}
