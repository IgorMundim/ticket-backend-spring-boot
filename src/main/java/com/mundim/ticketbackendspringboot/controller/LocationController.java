package com.mundim.ticketbackendspringboot.controller;


import com.mundim.ticketbackendspringboot.dto.request.ImageRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;
import com.mundim.ticketbackendspringboot.service.ILocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/event")
public class LocationController {
    private final ILocationService iLocationService;
    @PostMapping("/{id}/location")
    public ResponseEntity<LocationResponseDto> create(
            @RequestBody @Valid LocationRequestDto locationDto,
            @PathVariable Long id
            ){
        LocationResponseDto responseDto = iLocationService.create(locationDto, id);
        return ResponseEntity.status(201).body(responseDto);
    }
    @GetMapping("/location/{id}")
    public ResponseEntity<LocationResponseDto> getById(@PathVariable Long id){
        LocationResponseDto responseDto = iLocationService.fetch(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/location/{id}")
    public ResponseEntity<LocationResponseDto> update(@RequestBody @Valid LocationRequestDto locationDto, @PathVariable Long id){
        LocationResponseDto responseDto = iLocationService.update(locationDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/{id}/location")
    public ResponseEntity<List<LocationResponseDto>> findAllByIdEvent(@PathVariable Long id){
        List<LocationResponseDto> responseDto = iLocationService.fetchAll(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> getByString(@ModelAttribute @Valid ImageRequestDto responseDto ){
        return ResponseEntity.status(200).body(responseDto.getUrl().getOriginalFilename());
    }
}
