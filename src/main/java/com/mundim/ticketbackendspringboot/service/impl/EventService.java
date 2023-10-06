package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.EventMapper;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EventService implements IEventService {
    private final EventRepository eventRepository;


    @Override
    public EventResponseDto createEvent(EventRequestDto eventDto) {
        Event event = EventMapper.toEvent(eventDto);
        eventRepository.save(event);
        return EventMapper.toDto(event);
    }

    @Override
    public EventResponseDto fetchEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        return EventMapper.toDto(event);
    }

    @Override
    public EventResponseDto updateEvent(EventRequestDto eventDto, Long id) {
        eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        Event event = EventMapper.toEvent(eventDto);
        event.setId(id);
        eventRepository.save(event);
        return EventMapper.toDto(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        eventRepository.deleteById(id);
    }
}
