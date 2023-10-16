package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.service.IAccountAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/account")
public class AccountAddressController {
    private final IAccountAddressService iAccountAddressService;
    @PostMapping("/{id}/address")
    public ResponseEntity<AddressResponseDto> create(
            @RequestBody @Valid AddressRequestDto addressDto,
            @PathVariable Long id
            ){
        AddressResponseDto responseDto = iAccountAddressService.create(addressDto, id);
        return ResponseEntity.status(201).body(responseDto);
    }
    @GetMapping("/address/{id}")
    public ResponseEntity<AddressResponseDto> getById(@PathVariable Long id){
        AddressResponseDto responseDto = iAccountAddressService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @PatchMapping("/address/{id}")
    public ResponseEntity<AddressResponseDto> update(@RequestBody @Valid AddressRequestDto addressDto, @PathVariable Long id){
        AddressResponseDto responseDto = iAccountAddressService.update(addressDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

}
