package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;
import com.mundim.ticketbackendspringboot.entity.Location;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockLocation;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LocationServiceTest {
    MockLocation inputLocation;

    @InjectMocks
    LocationService service;
    @Mock
    EventRepository eventRepository;
    @Mock
    LocationRepository locationRepository;

    @BeforeEach
    void setUpMocks() throws Exception{
        inputLocation = new MockLocation();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should not accept the creation of an 'Event Address' with an invalid event id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetLocationToCreateAddress(){
        LocationRequestDto entityDto = inputLocation.mockDto(1);
        when(eventRepository.findById(1L)).thenReturn(
                Optional.empty()
        );
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.create(entityDto, 1L); });
        String expectedMessage = "Event not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found an 'Location' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetLocationById(){
        Location entity = inputLocation.mockEntity(1);
        when(locationRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetch(entity.getId()); });
        String expectedMessage = "Location not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found an 'Location' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdateLocationById(){
        Location entity = inputLocation.mockEntity(1);
        LocationRequestDto entityDto = inputLocation.mockDto(1);
        when(locationRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Location not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should create an 'Location'")
    void testWillCreateLocation(){
        Location entity = inputLocation.mockEntity(1);

        LocationRequestDto entityDto = inputLocation.mockDto(1);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(entity.getEvent()));
        LocationResponseDto result = service.create(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(LocationResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/location/{id}>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals("NameTest1", result.getName());
        assertEquals("DescriptionTest1", result.getDescription());
        assertEquals(true, result.getIsActive());
        assertEquals(10, result.getStorePrice());
        assertEquals(10, result.getSalePrice());
        assertEquals(10, result.getStudentPrice());
        assertEquals(10, result.getUnitsSolid());
        assertEquals(10, result.getUnits());
    }
    @Test
    @DisplayName("It should get an 'Location' by id")
    void testWillGetLocationById(){
        Location entity = inputLocation.mockEntity(1);

        when(locationRepository.findById(1L)).thenReturn(Optional.of(entity));
        LocationResponseDto result = service.fetch(entity.getId());
        assertNotNull(result);
        assertEquals(LocationResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/location/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals("NameTest1", result.getName());
        assertEquals("DescriptionTest1", result.getDescription());
        assertEquals(true, result.getIsActive());
        assertEquals(10, result.getStorePrice());
        assertEquals(10, result.getSalePrice());
        assertEquals(10, result.getStudentPrice());
        assertEquals(10, result.getUnitsSolid());
        assertEquals(10, result.getUnits());
    }
    @Test
    @DisplayName("It should get all 'Location' by event id")
    void testWillGetAllLocationByEventId(){
        List<Location> entities = inputLocation.mockEntityList();

        when(locationRepository.findByEventId(2L)).thenReturn(entities);
        List<LocationResponseDto> result = service.fetchAll(2L);
        assertNotNull(result);
        LocationResponseDto firstLocation = result.get(1);
        assertEquals(LocationResponseDto.class, firstLocation.getClass());
        assertTrue(firstLocation.getLinks().toString().contains("</api/v1/event/location/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals("NameTest1", firstLocation.getName());
        assertEquals("DescriptionTest1", firstLocation.getDescription());
        assertEquals(true, firstLocation.getIsActive());
        assertEquals(10, firstLocation.getStorePrice());
        assertEquals(10, firstLocation.getSalePrice());
        assertEquals(10, firstLocation.getStudentPrice());
        assertEquals(10, firstLocation.getUnitsSolid());
        assertEquals(10, firstLocation.getUnits());
    }
    @Test
    @DisplayName("It should update an 'Location' by id")
    void testWillUpdateLocationById(){
        Location entity = inputLocation.mockEntity(1);

        LocationRequestDto entityDto = inputLocation.mockDto(2);
        when(locationRepository.findById(1L)).thenReturn(Optional.of(entity));
        LocationResponseDto result = service.update(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(LocationResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/location/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals("NameTest2", result.getName());
        assertEquals("DescriptionTest2", result.getDescription());
        assertEquals(true, result.getIsActive());
        assertEquals(10, result.getStorePrice());
        assertEquals(10, result.getSalePrice());
        assertEquals(10, result.getStudentPrice());
        assertEquals(10, result.getUnitsSolid());
        assertEquals(10, result.getUnits());
    }
}