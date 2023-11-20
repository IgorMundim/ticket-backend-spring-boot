package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.service.IBatchService;
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
        name = "CRUD REST APIs for Batch",
        description = "CRUD REST APIs in Batch to CREATE, UPDATE  and FETCH account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/event", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BatchController {
    private final IBatchService iBatchService;
    @Operation(
            summary = "Create Event Batch REST API",
            description = "REST API to create new Event Batch"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found with the given input data",
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
                    responseCode = "403",
                    description = "Forbidden",
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
    @PostMapping("/{id}/batch")
    public ResponseEntity<BatchResponseDto> create(
            @RequestBody @Valid BatchRequestDto batchDto,
            @PathVariable Long id
            ){
        BatchResponseDto responseDto = iBatchService.create(batchDto, id);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Event Batch REST API",
            description = "REST API to fetch Event Batch"
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
    @GetMapping("/batch/{id}")
    public ResponseEntity<BatchResponseDto> getById(@PathVariable Long id){
        BatchResponseDto responseDto = iBatchService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "Update Event Batch REST API",
            description = "REST API to update Event Batch"
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
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
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
    @PatchMapping("/batch/{id}")
    public ResponseEntity<BatchResponseDto> update(@RequestBody @Valid BatchRequestDto batchDto, @PathVariable Long id){
        BatchResponseDto responseDto = iBatchService.update(batchDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "Fetch all Event Batches REST API",
            description = "REST API to fetch all Event Batch"
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
    @GetMapping("/{id}/batch")
    public ResponseEntity<List<BatchResponseDto>> getAllByIdEvent(@PathVariable Long id){
        List<BatchResponseDto> a = iBatchService.fetchAll(id);
        return ResponseEntity.status(200).body(a);
    }

}
