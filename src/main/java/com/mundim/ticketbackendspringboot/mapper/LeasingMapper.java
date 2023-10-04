package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.LeasingRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.LeasingResponseDto;
import com.mundim.ticketbackendspringboot.entity.Leasing;
import org.modelmapper.ModelMapper;

public class LeasingMapper {
    public static Leasing toLeasing(LeasingRequestDto leasingDto){
        return new ModelMapper().map(leasingDto, Leasing.class);
    }

    public static LeasingResponseDto toDto(Leasing leasing){
        return  new ModelMapper().map(leasing, LeasingResponseDto.class);
    }
}
