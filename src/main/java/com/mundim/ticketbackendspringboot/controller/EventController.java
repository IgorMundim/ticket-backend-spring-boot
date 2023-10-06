package com.mundim.ticketbackendspringboot.controller;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.ResponseDto;
import com.mundim.ticketbackendspringboot.service.IEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/event")
public class EventController {
    private final IEventService iEventService;
    @PostMapping("/")
    public ResponseEntity<EventResponseDto> create(@RequestBody @Valid EventRequestDto eventDto){
        EventResponseDto responseDto = iEventService.createEvent(eventDto);
        return ResponseEntity.status(201).body(responseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable Long id){
        EventResponseDto responseDto = iEventService.fetchEvent(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long id){
        iEventService.deleteEvent(id);
        return ResponseEntity.status(200).body(new ResponseDto("Request processed successfully"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventResponseDto> update(@RequestBody @Valid EventRequestDto eventDto, @PathVariable Long id){
        EventResponseDto responseDto = iEventService.updateEvent(eventDto, id);
        return ResponseEntity.status(200).body(responseDto);
    }

}
