package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.LocationRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.LocationResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.entity.Location;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.repository.LocationRepository;
import com.mundim.ticketbackendspringboot.service.ILocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class LocationService implements ILocationService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    @Transactional
    @Override
    public LocationResponseDto create(LocationRequestDto locationDto, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );

        Location location = Mapper.map(locationDto, Location.class);
        location.setEvent(event);
        locationRepository.save(location);
        return Mapper.map(location, LocationResponseDto.class);
    }


    @Override
    public LocationResponseDto fetch(Long id) {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Location", "id", Long.toString(id))
        );
        return Mapper.map(location, LocationResponseDto.class);
    }

    @Override
    public LocationResponseDto update(LocationRequestDto locationDto, Long id) {
        locationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Location", "id", Long.toString(id))
        );
        Location location = Mapper.map(locationDto, Location.class);
        location.setId(id);
        locationRepository.save(location);
        return Mapper.map(location, LocationResponseDto.class);
    }

    @Override
    public List<LocationResponseDto> fetchAll(Long id) {
        List<Location> location = locationRepository.findByEventId(id);
        return  Mapper.mapList(location, LocationResponseDto.class);
    }

}
