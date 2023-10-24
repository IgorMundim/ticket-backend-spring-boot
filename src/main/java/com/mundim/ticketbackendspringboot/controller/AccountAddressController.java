package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.service.IAccountAddressService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Address of an Account",
        description = "CRUD REST APIs in Address to CREATE, UPDATE  and FETCH account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountAddressController {
    private final IAccountAddressService iAccountAddressService;
    @Operation(
            summary = "Create Account Address REST API",
            description = "REST API to create new Account Address"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Address already registered",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource not found with the given input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/{id}/address")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<AddressResponseDto> create(
            @RequestBody @Valid AddressRequestDto addressDto,
            @PathVariable Long id
            ){
        AddressResponseDto responseDto = iAccountAddressService.create(addressDto, id);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Account Address REST API",
            description = "REST API to fetch Account Address"
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
    @GetMapping("/address/{id}")
    public ResponseEntity<AddressResponseDto> getById(@PathVariable Long id){
        AddressResponseDto responseDto = iAccountAddressService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "UPDATE Account REST API",
            description = "REST API to update Account ADMIN"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Already registered",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
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
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/address/{id}")
    public ResponseEntity<AddressResponseDto> update(@RequestBody @Valid AddressRequestDto addressDto, @PathVariable Long id){
        AddressResponseDto responseDto = iAccountAddressService.update(addressDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

}
