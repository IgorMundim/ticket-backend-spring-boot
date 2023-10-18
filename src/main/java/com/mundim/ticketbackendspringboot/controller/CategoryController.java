package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final ICategoryService iCategoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody @Valid CategoryRequestDto categoryDto){
        CategoryResponseDto responseDto = iCategoryService.create(categoryDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id){
        CategoryResponseDto responseDto = iCategoryService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long id){
        iCategoryService.delete(id);
        return ResponseEntity.status(200).body(new ResponseDto("Request processed successfully"));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@RequestBody @Valid CategoryRequestDto eventDto, @PathVariable Long id){
        CategoryResponseDto responseDto = iCategoryService.update(eventDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

}
