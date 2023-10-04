package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.Address;
import com.mundim.ticketbackendspringboot.mapper.AddressMapper;
import com.mundim.ticketbackendspringboot.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping("/address")
    public ResponseEntity<AddressResponseDto> create(@RequestBody @Valid AddressRequestDto addressDto){
        Address address = AddressMapper.toAddress(addressDto);
        addressService.create(address);
        return ResponseEntity.status(201).body(AddressMapper.toDto(address));
    }
}
