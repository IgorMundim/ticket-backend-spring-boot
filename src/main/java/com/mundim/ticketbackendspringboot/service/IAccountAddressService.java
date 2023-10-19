package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;

public interface IAccountAddressService {
    /**
     *
     * @param addressDto - AddressDto Object
     * @param id - id of account
     */
    AddressResponseDto create(AddressRequestDto addressDto, Long id);

    /**
     *
     * @param id - Input Address ID
     * @return Address Details based on a given id
     */
    AddressResponseDto fetch(Long id);

    /**
     *
     * @param addressDto - AddressDto Object
     * @param id - id of event
     * @return boolean indicating if the update of Address details is successful or not
     */
    AddressResponseDto update(AddressRequestDto addressDto, Long id);

}
