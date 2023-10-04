package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import org.modelmapper.ModelMapper;

public class EventMapper {
    public static EventResponseDto toEventBasicDto(Event event){
        return new ModelMapper().map(event, EventResponseDto.class);
    }
}
