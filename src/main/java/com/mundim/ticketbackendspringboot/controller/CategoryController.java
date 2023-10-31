package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "CRUD REST APIs for Category",
        description = "CRUD REST APIs in Category to CREATE, UPDATE  and FETCH account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {
    private final ICategoryService iCategoryService;
    @Operation(
            summary = "Create Category REST API",
            description = "REST API to create new Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Category already registered",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody @Valid CategoryRequestDto categoryDto){
        CategoryResponseDto responseDto = iCategoryService.create(categoryDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Category REST API",
            description = "REST API to fetch Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found with the given input data",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id){
        CategoryResponseDto responseDto = iCategoryService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "Fetch Category REST API",
            description = "REST API to fetch Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/category/")
    public ResponseEntity<List<CategoryResponseDto>> getAll(){
        List<CategoryResponseDto> responseDto = iCategoryService.fetchAll();
        return ResponseEntity.status(200).body(responseDto);
    }
    @GetMapping("/event/{id}/category")
    public ResponseEntity<List<CategoryResponseDto>> getAllByEventId(@PathVariable Long id){
        List<CategoryResponseDto> responseDto = iCategoryService.fetchAllByEventId(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "DELETE Category REST API",
            description = "REST API to delete Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/category/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id){
        iCategoryService.delete(id);
        return ResponseEntity.status(200).body(new ResponseDto("Request processed successfully"));
    }
    @Operation(
            summary = "UPDATE Category REST API",
            description = "REST API to update Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Category already registered",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> update(@RequestBody @Valid CategoryRequestDto eventDto, @PathVariable Long id){
        CategoryResponseDto responseDto = iCategoryService.update(eventDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

}
