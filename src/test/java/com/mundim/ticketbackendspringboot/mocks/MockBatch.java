package com.mundim.ticketbackendspringboot.mocks;


import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.entity.Batch;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MockBatch {
    public Batch mockEntity(){
        return mockEntity(0);
    }
    public BatchRequestDto mockDto(){
        return mockDto(0);
    }

    public List<Batch> mockEntityList(){
        List<Batch> entities = new ArrayList<Batch>();
        for(int i =0; i<14; i++){
            entities.add(mockEntity(i));
        }
        return entities;
    }

    public List<BatchRequestDto> mockDtoList(){
        List<BatchRequestDto> entities = new ArrayList<BatchRequestDto>();
        for(int i =0; i<14; i++){
            entities.add(mockDto(i));
        }
        return entities;
    }
    public Batch mockEntity(Integer number){
        Batch entity = new Batch();
        entity.setId(number.longValue());
        entity.setPercentage(10.1);
        entity.setBatchStopDate(LocalDateTime.of(2024, Month.FEBRUARY, 28, 25,1,1));
        entity.setSalesQtd(10);
        entity.setDescription("DescriptionTest"+number);
        entity.setIsActive(true);
        return entity;
    }

    public BatchRequestDto mockDto(Integer number){
        BatchRequestDto entity = new BatchRequestDto();
        entity.setPercentage(10.1);
        entity.setBatchStopDate(LocalDateTime.of(2024, Month.FEBRUARY, 28, 25,1,1));
        entity.setSalesQtd(10);
        entity.setDescription("DescriptionTest"+number);
        entity.setIsActive(true);
        return entity;
    }
}
