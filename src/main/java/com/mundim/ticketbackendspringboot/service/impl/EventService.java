package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;


    @Override
    public EventResponseDto create(EventRequestDto eventDto) {
        Event event = Mapper.map(eventDto, Event.class);
        eventRepository.save(event);
        return Mapper.map(event, EventResponseDto.class);
    }

    @Override
    public EventResponseDto fetch(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        return Mapper.map(event, EventResponseDto.class);
    }

    @Override
    public EventResponseDto update(EventRequestDto eventDto, Long id) {
        eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        Event event = Mapper.map(eventDto, Event.class);
        event.setId(id);
        eventRepository.save(event);
        return Mapper.map(event, EventResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        eventRepository.deleteById(id);
    }
}
