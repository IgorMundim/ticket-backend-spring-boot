package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.controller.EventController;
import com.mundim.ticketbackendspringboot.controller.LocationController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
        return Mapper.map(location, LocationResponseDto.class)
                .add(linkTo(methodOn(LocationController.class).getById(location.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(location.getEvent().getId())).withRel("Event"));
    }


    @Override
    public LocationResponseDto fetch(Long id) {
        Location location = locationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Location", "id", Long.toString(id))
        );
        return Mapper.map(location, LocationResponseDto.class)
                .add(linkTo(methodOn(LocationController.class).getById(location.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(location.getEvent().getId())).withRel("Event"));
    }

    @Override
    public LocationResponseDto update(LocationRequestDto locationDto, Long id) {
        Location oldLocation = locationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Location", "id", Long.toString(id))
        );
        Location location = Mapper.map(locationDto, Location.class);
        oldLocation.setName(location.getName());
        oldLocation.setDescription(location.getDescription());
        oldLocation.setIsActive(location.getIsActive());
        oldLocation.setStorePrice(location.getStorePrice());
        oldLocation.setSalePrice(location.getSalePrice());
        oldLocation.setStudentPrice(location.getStudentPrice());
        oldLocation.setUnitsSolid(location.getUnitsSolid());
        oldLocation.setUnits(location.getUnits());
        locationRepository.save(oldLocation);
        return Mapper.map(oldLocation, LocationResponseDto.class)
                .add(linkTo(methodOn(LocationController.class).getById(id)).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(oldLocation.getEvent().getId())).withRel("Event"));
    }

    @Override
    public List<LocationResponseDto> fetchAll(Long id) {
        List<LocationResponseDto> locations = Mapper.mapList(locationRepository.findByEventId(id),LocationResponseDto.class );
        locations
                .forEach(p -> Mapper.map(p, LocationResponseDto.class)
                        .add(linkTo(methodOn(LocationController.class).getById(p.getId())).withSelfRel())
                        .add(linkTo(methodOn(EventController.class).getById(id)).withRel("Event")));
        return  locations;
    }

}
