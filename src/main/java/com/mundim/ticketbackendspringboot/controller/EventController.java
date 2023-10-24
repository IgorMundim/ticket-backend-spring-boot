package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.service.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(
        name = "CRUD REST APIs for Event",
        description = "CRUD REST APIs in Event to CREATE, UPDATE, FETCH and DELETE account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/event", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EventController {
    private final IEventService iEventService;
    @Operation(
            summary = "Create Event REST API",
            description = "REST API to create new Event"
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
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/")
    public ResponseEntity<EventResponseDto> create(@RequestBody @Valid EventRequestDto eventDto){
        EventResponseDto responseDto = iEventService.create(eventDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Event REST API",
            description = "REST API to fetch Event"
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
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable Long id){
        EventResponseDto responseDto = iEventService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Operation(
            summary = "Fetch Event REST API",
            description = "REST API to fetch Event"
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
    @GetMapping("/")
    public ResponseEntity<PagedModel<EntityModel<EventResponseDto>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortDirection, "id"));
        PagedModel<EntityModel<EventResponseDto>> responseDto = iEventService.findAll(pageable);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "DELETE Event REST API",
            description = "REST API to delete Event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content
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
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id){
        iEventService.delete(id);
        return ResponseEntity.status(200).body(new ResponseDto("Request processed successfully"));
    }
    @Operation(
            summary = "UPDATE Event REST API",
            description = "REST API to update Event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
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
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/{id}")
    public ResponseEntity<EventResponseDto> update(@RequestBody @Valid EventRequestDto eventDto, @PathVariable Long id){
        EventResponseDto responseDto = iEventService.update(eventDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

}
