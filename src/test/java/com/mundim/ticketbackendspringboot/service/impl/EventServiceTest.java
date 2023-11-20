package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.CategoryRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.EventRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.CategoryResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.EventResponseDto;
import com.mundim.ticketbackendspringboot.entity.Category;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockCategory;
import com.mundim.ticketbackendspringboot.mocks.MockEvent;
import com.mundim.ticketbackendspringboot.repository.CategoryRepository;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EventServiceTest {
    MockEvent input;
    @InjectMocks
    EventService service;

    @Mock
    EventRepository eventRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockEvent();
        MockitoAnnotations.openMocks(this);
    }



    @Test
    @DisplayName("It should not find 'Event' with invalid id when trying update the event.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdateEventById(){
        Event entity = input.mockEntity(1);
        EventRequestDto entityDto = input.mockDto(1);
        when(eventRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Event not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should not find 'Event' with invalid id when trying delete the event.")
    void testWillThrowResourceNotFoundExceptionWhenTryDeleteEventById(){
        Event entity = input.mockEntity(1);
        when(eventRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.delete( 1L); });
        String expectedMessage = "Event not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should create a Event")
    void testWillCreateEvent(){
        Event entity = input.mockEntity(1);
        EventRequestDto entityDto = input.mockDto(1);

        EventResponseDto result = service.create(entityDto);
        assertNotNull(result);
        assertEquals(EventResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/{id}>;rel=\"self\",</api/v1/event/{id}/location>;rel=\"Locations\",</api/v1/event/{id}/batch>;rel=\"Batch\",</api/v1/event/{id}/category>;rel=\"Category\",</api/v1/event/{id}/address/>;rel=\"Address\""));
        assertEquals("NameTest1", result.getName());
        assertEquals(true, result.getIsActive());
        assertEquals(true, result.getIsVirtual());
        assertEquals(true,result.getIsPublished());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 23,1,1), result.getDateEnd());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1), result.getDateStart());
        assertEquals("DescriptionTest1", result.getDescription());
        assertEquals("VideoUrlTest1", result.getVideoUrl());
    }

    @Test
    @DisplayName("It should get an 'Event'")
    void testFetchEventById(){
        Event entity = input.mockEntity(1);
        when(eventRepository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));
        var result = service.fetch(entity.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(EventResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/1>;rel=\"self\",</api/v1/event/1/location>;rel=\"Locations\",</api/v1/event/1/batch>;rel=\"Batch\",</api/v1/event/1/category>;rel=\"Category\",</api/v1/event/1/address/>;rel=\"Address\""));
        assertEquals("NameTest1", result.getName());
        assertEquals(true, result.getIsActive());
        assertEquals(true, result.getIsVirtual());
        assertEquals(true,result.getIsPublished());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 23,1,1), result.getDateEnd());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1), result.getDateStart());
        assertEquals("DescriptionTest1", result.getDescription());
        assertEquals("VideoUrlTest1", result.getVideoUrl());

    }
    @Test
    @DisplayName("It should update a 'Event'")
    void testWillUpdateEvent(){
        Event oldEntity = input.mockEntity(1);

        EventRequestDto entityDto = input.mockDto(2);
        when(eventRepository.findById(oldEntity.getId())).thenReturn(
                Optional.of(oldEntity)
        );

        EventResponseDto result = service.update(entityDto, 1L);
        assertNotNull(result);
        assertEquals(EventResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/1>;rel=\"self\",</api/v1/event/1/location>;rel=\"Locations\",</api/v1/event/1/batch>;rel=\"Batch\",</api/v1/event/1/category>;rel=\"Category\",</api/v1/event/1/address/>;rel=\"Address\""));
        assertEquals("NameTest2", result.getName());
        assertEquals(true, result.getIsActive());
        assertEquals(true, result.getIsVirtual());
        assertEquals(true,result.getIsPublished());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 23,1,1), result.getDateEnd());
        assertEquals(LocalDateTime.of(2024, Month.FEBRUARY, 28, 22,1,1), result.getDateStart());
        assertEquals("DescriptionTest2", result.getDescription());
        assertEquals("VideoUrlTest2", result.getVideoUrl());
    }

    @Test
    @DisplayName("It should delete a 'Event'")
    void testDeleteCategory(){
        Event entity = input.mockEntity(1);
        when(eventRepository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));
        service.delete(1L);
    }

}