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
        MockAccount inputAccount = new MockAccount();
        entity.setId(number.longValue());
        entity.setMobileNumber("123456789123");
        entity.setZipcode("12345678");
        entity.setComplement("ComplementTest"+number);
        entity.setNeighborhood("NeighborhoodTest"+number);
        entity.setNumber("NumberTest"+number);
        entity.setCity("CityTest"+number);
        entity.setStreet("StreetTest"+number);
        entity.setUf("MG");
        entity.setAccount(inputAccount.mockEntity(1));
        return entity;
    }

    public AddressRequestDto mockDto(Integer number){
        AddressRequestDto entity = new AddressRequestDto();
        entity.setMobileNumber("123456789123");
        entity.setZipcode("12345678");
        entity.setComplement("ComplementTest"+number);
        entity.setNeighborhood("NeighborhoodTest"+number);
        entity.setNumber("123456");
        entity.setCity("CityTest"+number);
        entity.setStreet("StreetTest"+number);
        entity.setUf("MG");

        return entity;
    }
}
