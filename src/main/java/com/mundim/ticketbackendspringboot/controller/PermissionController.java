package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.PermissionRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ErrorResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.PermissionResponseDto;
import com.mundim.ticketbackendspringboot.service.IPermissionService;
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
        name = "CRUD REST APIs for Permission",
        description = "CRUD REST APIs in Permission to CREATE, UPDATE  and FETCH account details"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(path="api/v1/permission", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PermissionController {
    private final IPermissionService iPermissionService;
    @Operation(
            summary = "Create Permission REST API",
            description = "REST API to create new Permission"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Permission already registered",
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
    @PostMapping("/")
    public ResponseEntity<PermissionResponseDto> create(@RequestBody @Valid PermissionRequestDto permissionDto){
        PermissionResponseDto responseDto = iPermissionService.create(permissionDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @Operation(
            summary = "Fetch Permission REST API",
            description = "REST API to fetch Permission"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
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
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> getById(@PathVariable Integer id){
        PermissionResponseDto responseDto = iPermissionService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "UPDATE Permission REST API",
            description = "REST API to update Permission"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Permission already registered",
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
    @PatchMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> update(@RequestBody @Valid PermissionRequestDto permissionDto, @PathVariable Integer id){
        PermissionResponseDto responseDto = iPermissionService.update(permissionDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @Operation(
            summary = "Fetch all Permissions REST API",
            description = "REST API to fetch all Permissions"
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
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/")
    public ResponseEntity<List<PermissionResponseDto>> getAll(){
        List<PermissionResponseDto> responseDto = iPermissionService.fetchAll();
        return ResponseEntity.status(200).body(responseDto);
    }

}
