package com.mundim.ticketbackendspringboot.mocks;


import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.entity.Location;

import java.util.ArrayList;
import java.util.List;

public class MockLocation {
    public Location mockEntity(){
        return mockEntity(0);
    }
    public LocationRequestDto mockDto(){
        return mockDto(0);
    }

    public List<Location> mockEntityList(){
        List<Location> entities = new ArrayList<Location>();
        for(int i =0; i<14; i++){
            entities.add(mockEntity(i));
        }
        return entities;
    }

    public List<LocationRequestDto> mockDtoList(){
        List<LocationRequestDto> entities = new ArrayList<LocationRequestDto>();
        for(int i =0; i<14; i++){
            entities.add(mockDto(i));
        }
        return entities;
    }
    public Location mockEntity(Integer number){
        Location entity = new Location();
        entity.setId(number.longValue());
        entity.setName("NameTest"+number);
        entity.setDescription("DescriptionTest"+number);
        entity.setIsActive(true);
        entity.setStorePrice(10L);
        entity.setSalePrice(10L);
        entity.setStudentPrice(10L);
        entity.setUnitsSolid(10);
        entity.setUnits(10);
        return entity;
    }

    public LocationRequestDto mockDto(Integer number){
        LocationRequestDto entity = new LocationRequestDto();
        entity.setName("NameTest"+number);
        entity.setDescription("DescriptionTest"+number);
        entity.setIsActive(true);
        entity.setStorePrice(10L);
        entity.setSalePrice(10L);
        entity.setStudentPrice(10L);
        entity.setUnitsSolid(10);
        entity.setUnits(10);
        return entity;
    }
}
