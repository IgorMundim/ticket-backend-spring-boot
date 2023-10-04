package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.BatchRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.BatchResponseDto;
import com.mundim.ticketbackendspringboot.entity.Batch;
import org.modelmapper.ModelMapper;

public class BatchMapper {
    public static Batch toBatch(BatchRequestDto batchDto){
        return new ModelMapper().map(batchDto, Batch.class);
    }

    public static BatchResponseDto toDto(Batch batch){
        return  new ModelMapper().map(batch, BatchResponseDto.class);
    }
}
