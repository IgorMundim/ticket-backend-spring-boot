package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryService {
    /**
     *
     * @param categoryDto - CategoryDto Object
     */
    CategoryResponseDto create(CategoryRequestDto categoryDto);

    /**
     *
     * @param id - Input Category ID
     * @return Category Details based on a given id
     */
    CategoryResponseDto fetch(Long id);

    /**
     *
     * @param id - Input Event ID
     * @return Category Details based on a given id
     */
    List<CategoryResponseDto> fetchAllByEventId(Long id);
    /**
     *
     * @return All Category Details
     */
    List<CategoryResponseDto> fetchAll();

    /**
     *
     * @param categoryDto - CategoryDto Object
     * @return boolean indicating if the update of Event details is successful or not
     */
    CategoryResponseDto update(CategoryRequestDto categoryDto, Long id);
    /**
     *
     * @param id - Input Category ID
     */
    void delete(Long id);
}
