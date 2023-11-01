package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.Address;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockAccountAddress;
import com.mundim.ticketbackendspringboot.repository.AccountRepository;
import com.mundim.ticketbackendspringboot.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountAddressServiceTest {
    MockAccountAddress inputAddress;

    @InjectMocks
    AccountAddressService service;
    @Mock
    AccountRepository accountRepository;
    @Mock
    AddressRepository addressRepository;

    @BeforeEach
    void setUpMocks() throws Exception{
        inputAddress = new MockAccountAddress();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should not accept the creation of an 'Account Address' with an invalid account id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetAccountToCreateAddress(){
        AddressRequestDto entityDto = inputAddress.mockDto();
        when(accountRepository.findById(1L)).thenReturn(
                Optional.empty()
        );
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.create(entityDto, 1L); });
        String expectedMessage = "Account not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found an 'Account Address' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetAddressById(){
        Address entity = inputAddress.mockEntity(1);
        when(addressRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetch(entity.getId()); });
        String expectedMessage = "Address not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found an 'Account Address' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdateAddressById(){
        Address entity = inputAddress.mockEntity(1);
        AddressRequestDto entityDto = inputAddress.mockDto(1);
        when(addressRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Address not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should create an 'Account Address'")
    void testWillCreateAddress(){
        Address entity = inputAddress.mockEntity(1);

        AddressRequestDto entityDto = inputAddress.mockDto(1);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(entity.getAccount()));
        AddressResponseDto result = service.create(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(AddressResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/account/address/{id}>;rel=\"self\",</api/v1/account/1>;rel=\"Account\""));
        assertEquals("123456789123", result.getMobileNumber());
        assertEquals("12345678", result.getZipcode());
        assertEquals("ComplementTest1", result.getComplement());
        assertEquals("CityTest1", result.getCity());
        assertEquals("NeighborhoodTest1", result.getNeighborhood());
        assertEquals("123456",result.getNumber());
        assertEquals("StreetTest1", result.getStreet());
        assertEquals("MG", result.getUf());
    }
    @Test
    @DisplayName("It should get an 'Account Address' by id")
    void testWillGetAddressById(){
        Address entity = inputAddress.mockEntity(1);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(entity));
        AddressResponseDto result = service.fetch(entity.getId());
        assertNotNull(result);
        assertEquals(AddressResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/account/address/1>;rel=\"self\",</api/v1/account/1>;rel=\"Account\""));
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
    @DisplayName("It should update an 'Account Address' by id")
    void testWillUpdateAddressById(){
        Address entity = inputAddress.mockEntity(1);

        AddressRequestDto entityDto = inputAddress.mockDto(2);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(entity));
        AddressResponseDto result = service.update(entityDto, entity.getId());
        assertNotNull(result);
        assertEquals(AddressResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/account/address/1>;rel=\"self\",</api/v1/account/1>;rel=\"Account\""));
        assertEquals("123456789123", result.getMobileNumber());
        assertEquals("12345678", result.getZipcode());
        assertEquals("ComplementTest2", result.getComplement());
        assertEquals("CityTest2", result.getCity());
        assertEquals("NeighborhoodTest2", result.getNeighborhood());
        assertEquals("123456",result.getNumber());
        assertEquals("StreetTest2", result.getStreet());
        assertEquals("MG", result.getUf());
    }
}