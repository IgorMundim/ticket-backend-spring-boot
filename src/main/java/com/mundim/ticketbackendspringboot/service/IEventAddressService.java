package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;

public interface IEventAddressService {
    /**
     *
     * @param addressDto - AddressDto Object
     */
    AddressResponseDto createAddress(AddressRequestDto addressDto, Long id);

    /**
     *
     * @param id - Input Address ID
     * @return Address Details based on a given id
     */
    AddressResponseDto fetchAddress(Long id);

    /**
     *
     * @param addressDto - AddressDto Object
     * @return boolean indicating if the update of Address details is successful or not
     */
    AddressResponseDto updateAddress(AddressRequestDto addressDto, Long id);
    /**
     *
     * @param id - Input Address ID
     */
    void deleteAddress(Long id);
}
