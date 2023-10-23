package com.mundim.ticketbackendspringboot.service.impl;

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
        return Mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto fetch(Long id) {
        EventAddress address = eventAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        return Mapper.map(address, AddressResponseDto.class);
    }
    @Override
    public AddressResponseDto fetchByEventId(Long id) {
        EventAddress address = eventAddressRepository.findByEventId(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        return Mapper.map(address, AddressResponseDto.class);
    }
    @Override
    public AddressResponseDto update(AddressRequestDto addressDto, Long id) {
        eventAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        EventAddress address = Mapper.map(addressDto, EventAddress.class);
        address.setId(id);
        eventAddressRepository.save(address);
        return Mapper.map(address, AddressResponseDto.class);
    }

}
