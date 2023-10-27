package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.entity.EventAddress;

import java.util.ArrayList;
import java.util.List;

public class MockEventAddress {
    public EventAddress mockEntity(){
        return mockEntity(0);
    }
    public AddressRequestDto mockDto(){
        return mockDto(0);
    }

    public List<EventAddress> mockEntityList(){
        List<EventAddress> entities = new ArrayList<EventAddress>();
        for(int i =0; i<14; i++){
            entities.add(mockEntity(i));
        }
        return entities;
    }

    public List<AddressRequestDto> mockDtoList(){
        List<AddressRequestDto> entities = new ArrayList<AddressRequestDto>();
        for(int i =0; i<14; i++){
            entities.add(mockDto(i));
        }
        return entities;
    }
    public EventAddress mockEntity(Integer number){
        EventAddress entity = new EventAddress();
        entity.setId(number.longValue());
        entity.setMobileNumber("MobileNumberTest"+number);
        entity.setZipcode("12345678");
        entity.setComplement("ComplementTest"+number);
        entity.setNeighborhood("NeighborhoodTest"+number);
        entity.setCity("CityTest"+number);
        entity.setStreet("StreetTest"+number);
        entity.setUf("MG");
        return entity;
    }

    public AddressRequestDto mockDto(Integer number){
        AddressRequestDto entity = new AddressRequestDto();
        entity.setMobileNumber("MobileNumberTest"+number);
        entity.setZipcode("12345678");
        entity.setComplement("ComplementTest"+number);
        entity.setNeighborhood("NeighborhoodTest"+number);
        entity.setCity("CityTest"+number);
        entity.setStreet("StreetTest"+number);
        entity.setUf("MG");
        return entity;
    }
}
