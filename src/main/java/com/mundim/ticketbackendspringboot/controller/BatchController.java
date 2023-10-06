package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.service.IBatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/event")
public class BatchController {
    private final IBatchService iBatchService;
    @PostMapping("/{id}/batch")
    public ResponseEntity<BatchResponseDto> create(
            @RequestBody @Valid BatchRequestDto batchDto,
            @PathVariable Long id
            ){
        BatchResponseDto responseDto = iBatchService.createBatch(batchDto, id);
        return ResponseEntity.status(201).body(responseDto);
    }
    @GetMapping("/batch/{id}")
    public ResponseEntity<BatchResponseDto> getById(@PathVariable Long id){
        BatchResponseDto responseDto = iBatchService.fetchBatch(id);
        return ResponseEntity.status(200).body(responseDto);
    }
    @PatchMapping("/batch/{id}")
    public ResponseEntity<BatchResponseDto> update(@RequestBody @Valid BatchRequestDto batchDto, @PathVariable Long id){
        BatchResponseDto responseDto = iBatchService.updateBatch(batchDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/{id}/batch")
    public ResponseEntity<List<BatchResponseDto>> findAllByIdEvent(@PathVariable Long id){
        List<BatchResponseDto> a = iBatchService.fetchAllBatch(id);
        return ResponseEntity.status(200).body(a);
    }

}
