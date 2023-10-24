package com.mundim.ticketbackendspringboot.controller;


import com.mundim.ticketbackendspringboot.dto.request.ImageRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;
import com.mundim.ticketbackendspringboot.service.ILocationService;
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
        name = "CRUD REST APIs for Location",
        description = "CRUD REST APIs in Location to CREATE, UPDATE  and FETCH account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/event", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LocationController {
    private final ILocationService iLocationService;
    @Operation(
            summary = "Create Location REST API",
            description = "REST API to create new Event Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/{id}/location")
    public ResponseEntity<LocationResponseDto> create(
            @RequestBody @Valid LocationRequestDto locationDto,
            @PathVariable Long id
            ){
        LocationResponseDto responseDto = iLocationService.create(locationDto, id);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Event Location REST API",
            description = "REST API to fetch Event Location"
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
    @GetMapping("/location/{id}")
    public ResponseEntity<LocationResponseDto> getById(@PathVariable Long id){
        LocationResponseDto responseDto = iLocationService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Update Location REST API",
            description = "REST API to update Event Location"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
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
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/location/{id}")
    public ResponseEntity<LocationResponseDto> update(@RequestBody @Valid LocationRequestDto locationDto, @PathVariable Long id){
        LocationResponseDto responseDto = iLocationService.update(locationDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "Fetch all Event Location REST API",
            description = "REST API to fetch all Event Location"
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
    @GetMapping("/{id}/location")
    public ResponseEntity<List<LocationResponseDto>> getAllByIdEvent(@PathVariable Long id){
        List<LocationResponseDto> responseDto = iLocationService.fetchAll(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> getByString(@ModelAttribute @Valid ImageRequestDto responseDto ){
        return ResponseEntity.status(200).body(responseDto.getUrl().getOriginalFilename());
    }
}
