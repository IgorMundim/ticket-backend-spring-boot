package com.mundim.ticketbackendspringboot.mapper;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.Address;
import org.modelmapper.ModelMapper;

public class AddressMapper {
    public static Address toAddress(AddressRequestDto addressDto){
        return new ModelMapper().map(addressDto, Address.class);
    }

    public static AddressResponseDto toDto(Address address){
        return  new ModelMapper().map(address, AddressResponseDto.class);
    }
}
