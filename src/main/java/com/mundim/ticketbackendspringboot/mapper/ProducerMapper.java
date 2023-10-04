package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.ProducerRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.ProducerResponseDto;
import com.mundim.ticketbackendspringboot.entity.Producer;
import org.modelmapper.ModelMapper;

public class ProducerMapper {
    public static Producer toProducer(ProducerRequestDto producerDto){
        return new ModelMapper().map(producerDto, Producer.class);
    }

    public static ProducerResponseDto toDto(Producer producer){
        return  new ModelMapper().map(producer, ProducerResponseDto.class);
    }
}
