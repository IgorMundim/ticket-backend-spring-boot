package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.PasswordRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.service.IAccountService;
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
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for  Account",
        description = "CRUD REST APIs in Account to CREATE, UPDATE  and FETCH account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/account", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {
    private final IAccountService iAccountService;
    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Username already registered"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@Valid @RequestBody AccountRequestDto accountDto){
        AccountResponseDto responseDto = iAccountService.create(accountDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Create Account ADMIN REST API",
            description = "REST API to create new Account ADMIN"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Username already registered",
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
    @PostMapping(path = "/admin")
    public ResponseEntity<AccountResponseDto> createAdmin(@Valid @RequestBody AccountRequestDto accountDto){
        AccountResponseDto responseDto = iAccountService.createAdmin(accountDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Account REST API",
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
    @GetMapping(path = "/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<AccountResponseDto> fetchById(@Valid @PathVariable Long id){
        AccountResponseDto responseDto = iAccountService.fetchById(id);
        return ResponseEntity.ok().body(responseDto);
    }
    @Operation(
            summary = "Update Account PASSWORD REST API",
            description = "REST API to update Account PASSWORD"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
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
    @PatchMapping(path = "/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Void> updatePasswordById(@Valid @RequestBody PasswordRequestDto passwordDto, @PathVariable Long id){
        iAccountService.updatePassword(id, passwordDto);
        return ResponseEntity.noContent().build();
    }

}
