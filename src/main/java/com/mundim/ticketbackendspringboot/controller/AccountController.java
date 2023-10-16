package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.PasswordRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final IAccountService iAccountService;

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@Valid @RequestBody AccountRequestDto accountDto){
        AccountResponseDto responseDto = iAccountService.create(accountDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @PostMapping(path = "/admin")
    public ResponseEntity<AccountResponseDto> createAdmin(@Valid @RequestBody AccountRequestDto accountDto){
        AccountResponseDto responseDto = iAccountService.createAdmin(accountDto);
        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<AccountResponseDto> fetchById(@Valid @PathVariable Long id){
        AccountResponseDto responseDto = iAccountService.fetchById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @PatchMapping(path = "/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Void> updatePasswordById(@Valid @RequestBody PasswordRequestDto passwordDto, @PathVariable Long id){
        iAccountService.updatePassword(id, passwordDto);
        return ResponseEntity.noContent().build();
    }

}
