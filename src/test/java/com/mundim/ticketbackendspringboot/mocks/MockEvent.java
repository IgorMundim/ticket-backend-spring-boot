package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MockEvent {
    public Event mockEntity(){
        return mockEntity(0);
    }
    public EventRequestDto mockDto(){
        return mockDto(0);
    }

    public List<Event> mockEntityList(){
        List<Event> entities = new ArrayList<Event>();
        for(int i =0; i<14; i++){
            entities.add(mockEntity(i));
        }
        return entities;
    }

    public List<EventRequestDto> mockDtoList(){
        List<EventRequestDto> entities = new ArrayList<EventRequestDto>();
        for(int i =0; i<14; i++){
            entities.add(mockDto(i));
        }
        return entities;
    }
    public Event mockEntity(Integer number){
        Event entity = new Event();
        entity.setId(number.longValue());
        entity.setName("NameTest"+number);
        entity.setIsActive(true);
        entity.setIsVirtual(true);
        entity.setIsPublished(true);
        entity.setDateEnd(LocalDateTime.of(2024, Month.FEBRUARY, 28, 23,1,1));
        entity.setDateStart(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1));
        entity.setDescription("DescriptionTest"+number);
        entity.setVideoUrl("VideoUrlTest"+number);
        return entity;
    }

    public EventRequestDto mockDto(Integer number){
        EventRequestDto entity = new EventRequestDto();
        entity.setName("NameTest"+number);
        entity.setIsActive(true);
        entity.setIsVirtual(true);
        entity.setIsPublished(true);
        entity.setDateEnd(LocalDateTime.of(2024, Month.FEBRUARY, 28, 23,1,1));
        entity.setDateStart(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1));
        entity.setDescription("DescriptionTest"+number);
        entity.setVideoUrl("VideoUrlTest"+number);
        return entity;
    }
}
