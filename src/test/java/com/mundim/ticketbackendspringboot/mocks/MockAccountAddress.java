package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class MockAccountAddress {
    public Address mockEntity(){
        return mockEntity(0);
    }
    public AddressRequestDto mockDto(){
        return mockDto(0);
    }

    public List<Address> mockEntityList(){
        List<Address> entities = new ArrayList<Address>();
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
    public Address mockEntity(Integer number){
        Address entity = new Address();
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
