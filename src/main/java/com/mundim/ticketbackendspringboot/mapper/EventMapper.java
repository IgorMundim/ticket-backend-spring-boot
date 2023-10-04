package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import org.modelmapper.ModelMapper;

public class EventMapper {
    public static Event toEvent(EventRequestDto eventDto){
        return new ModelMapper().map(eventDto, Event.class);
    }

    public static EventResponseDto toDto(Event event){
        return  new ModelMapper().map(event, EventResponseDto.class);
    }
}
