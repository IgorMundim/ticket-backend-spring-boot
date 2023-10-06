package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;

import java.util.List;

public interface ILocationService {
    /**
     *
     * @param locationDto - LocationResponseDto Object
     * @param id - id of event
     */
    LocationResponseDto createLocation(LocationRequestDto locationDto, Long id);

    /**
     *
     * @param id - Input Location ID
     * @return Location Details based on a given id
     */
    LocationResponseDto fetchLocation(Long id);

    /**
     *
     * @param locationDto - LocationDto Object
     * @return boolean indicating if the update of Location details is successful or not
     */
    LocationResponseDto updateLocation(LocationRequestDto locationDto, Long id);

    /**
     *
     * @param id - Event id
     * @return - All location by id
     */
    List<LocationResponseDto> fetchAllLocation(Long id);
}
