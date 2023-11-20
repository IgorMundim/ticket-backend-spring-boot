package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.EventAddress;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockEventAddress;
import com.mundim.ticketbackendspringboot.repository.EventAddressRepository;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EventAddressServiceTest {
    MockEventAddress inputAddress;

    @InjectMocks
    EventAddressService service;
    @Mock
    EventRepository eventRepository;
    @Mock
    EventAddressRepository addressRepository;

    @BeforeEach
    void setUpMocks() throws Exception{
        inputAddress = new MockEventAddress();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should not accept the creation of an 'Event Address' with an invalid event id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetAccountToCreateAddress(){
        AddressRequestDto entityDto = inputAddress.mockDto(1);
        when(eventRepository.findById(1L)).thenReturn(
                Optional.empty()
        );
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.create(entityDto, 1L); });
        String expectedMessage = "Event not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found an 'Event Address' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetAddressById(){
        EventAddress entity = inputAddress.mockEntity(1);
        when(addressRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetch(entity.getId()); });
        String expectedMessage = "Address not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found an 'Event Address' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdateAddressById(){
        EventAddress entity = inputAddress.mockEntity(1);
        AddressRequestDto entityDto = inputAddress.mockDto(1);
        when(addressRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Address not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should create an 'Event Address'")
    void testWillCreateAddress(){
        EventAddress entity = inputAddress.mockEntity(1);

        AddressRequestDto entityDto = inputAddress.mockDto(1);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(entity.getEvent()));
        AddressResponseDto result = service.create(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(AddressResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/address/{id}>;rel=\"self\",</api/v1/event/1>;rel=\"Event\""));
        assertEquals("123456789123", result.getMobileNumber());
        assertEquals("12345678", result.getZipcode());
        assertEquals("ComplementTest1", result.getComplement());
        assertEquals("CityTest1", result.getCity());
        assertEquals("NeighborhoodTest1", result.getNeighborhood());
        assertEquals("NumberTest1",result.getNumber());
        assertEquals("StreetTest1", result.getStreet());
        assertEquals("MG", result.getUf());
    }
    @Test
    @DisplayName("It should get an 'Event Address' by id")
    void testWillGetAddressById(){
        EventAddress entity = inputAddress.mockEntity(1);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(entity));
        AddressResponseDto result = service.fetch(entity.getId());
        assertNotNull(result);
        assertEquals(AddressResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/address/1>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals("123456789123", result.getMobileNumber());
        assertEquals("12345678", result.getZipcode());
        assertEquals("ComplementTest1", result.getComplement());
        assertEquals("CityTest1", result.getCity());
        assertEquals("NeighborhoodTest1", result.getNeighborhood());
        assertEquals("NumberTest1",result.getNumber());
        assertEquals("StreetTest1", result.getStreet());
        assertEquals("MG", result.getUf());
    }

    @Test
    @DisplayName("It should update an 'Event Address' by id")
    void testWillUpdateAddressById(){
        EventAddress entity = inputAddress.mockEntity(1);

        AddressRequestDto entityDto = inputAddress.mockDto(2);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(entity));
        AddressResponseDto result = service.update(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(AddressResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/event/1/address/>;rel=\"self\",</api/v1/event/2>;rel=\"Event\""));
        assertEquals("123456789123", result.getMobileNumber());
        assertEquals("12345678", result.getZipcode());
        assertEquals("ComplementTest2", result.getComplement());
        assertEquals("CityTest2", result.getCity());
        assertEquals("NeighborhoodTest2", result.getNeighborhood());
        assertEquals("NumberTest2",result.getNumber());
        assertEquals("StreetTest2", result.getStreet());
        assertEquals("MG", result.getUf());
    }
}