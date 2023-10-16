package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AccountResponseDto> fetchById(@Valid @PathVariable Long id){
        AccountResponseDto responseDto = iAccountService.fetchById(id);
        return ResponseEntity.ok().body(responseDto);
    }
}
