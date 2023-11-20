package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.controller.AccountAddressController;
import com.mundim.ticketbackendspringboot.controller.AccountController;
import com.mundim.ticketbackendspringboot.controller.EventAddressController;
import com.mundim.ticketbackendspringboot.controller.EventController;
import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.entity.EventAddress;
import com.mundim.ticketbackendspringboot.exception.AlreadyExistsException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.EventAddressRepository;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.service.IEventAddressService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequiredArgsConstructor
@Service
public class EventAddressService implements IEventAddressService {
    private final EventRepository eventRepository;
    private final EventAddressRepository eventAddressRepository;
    @Transactional
    @Override
    public AddressResponseDto create(AddressRequestDto addressDto, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        EventAddress address = Mapper.map(addressDto, EventAddress.class);
        try {
            address.setEvent(event);
            eventAddressRepository.save(address);
        } catch (Exception ex){
            throw new AlreadyExistsException("Address already registered");
        }
        return Mapper.map(address, AddressResponseDto.class)
                .add(linkTo(methodOn(EventAddressController.class).getById(address.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(id)).withRel("Event"));

    }

    @Override
    public AddressResponseDto fetch(Long id) {
        EventAddress address = eventAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        return Mapper.map(address, AddressResponseDto.class)
                .add(linkTo(methodOn(EventAddressController.class).getById(id)).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(address.getEvent().getId())).withRel("Event"));
    }
    @Override
    public AddressResponseDto fetchByEventId(Long id) {
        EventAddress address = eventAddressRepository.findByEventId(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        return Mapper.map(address, AddressResponseDto.class)
                .add(linkTo(methodOn(EventAddressController.class).getById(address.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(id)).withRel("Event"));
    }
    @Override
    public AddressResponseDto update(AddressRequestDto addressDto, Long id) {
        EventAddress oldAddress = eventAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        EventAddress address = Mapper.map(addressDto, EventAddress.class);
        oldAddress.setMobileNumber(address.getMobileNumber());
        oldAddress.setZipcode(address.getZipcode());
        oldAddress.setComplement(address.getComplement());
        oldAddress.setCity(address.getCity());
        oldAddress.setNeighborhood(address.getNeighborhood());
        oldAddress.setNumber(address.getNumber());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setUf(address.getUf());
        eventAddressRepository.save(oldAddress);
        return Mapper.map(oldAddress, AddressResponseDto.class)
                .add(linkTo(methodOn(EventAddressController.class).getByEventId(oldAddress.getId())).withSelfRel())
                .add(linkTo(methodOn(EventController.class).getById(oldAddress.getEvent().getId())).withRel("Event"));
    }

}
