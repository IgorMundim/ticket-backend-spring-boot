package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;

public interface ICategoryService {
    /**
     *
     * @param categoryDto - CategoryDto Object
     */
    CategoryResponseDto createCategory(CategoryRequestDto categoryDto);

    /**
     *
     * @param id - Input Category ID
     * @return Category Details based on a given id
     */
    CategoryResponseDto fetchCategory(Long id);

    /**
     *
     * @param categoryDto - CategoryDto Object
     * @return boolean indicating if the update of Event details is successful or not
     */
    CategoryResponseDto updateCategory(CategoryRequestDto categoryDto, Long id);
    /**
     *
     * @param id - Input Category ID
     */
    void deleteCategory(Long id);
}