package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.Address;
import com.mundim.ticketbackendspringboot.entity.Event;
import com.mundim.ticketbackendspringboot.exception.AddressAlreadyExistsException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.AddressMapper;
import com.mundim.ticketbackendspringboot.mapper.EventMapper;
import com.mundim.ticketbackendspringboot.repository.AddressRepository;
import com.mundim.ticketbackendspringboot.repository.EventRepository;
import com.mundim.ticketbackendspringboot.service.IEventAddressService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EventAddressService implements IEventAddressService {
    private final EventRepository eventRepository;
    private final AddressRepository addressRepository;
    @Transactional
    @Override
    public AddressResponseDto createAddress(AddressRequestDto addressDto, Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event", "id", Long.toString(id))
        );
        if(event.getAddress() != null){
            throw new AddressAlreadyExistsException("Address already registered");
        }
        Address address = AddressMapper.toAddress(addressDto);
        event.setAddress(address);
        eventRepository.save(event);
        return AddressMapper.toDto(address);
    }

    @Override
    public AddressResponseDto fetchAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        return AddressMapper.toDto(address);
    }

    @Override
    public AddressResponseDto updateAddress(AddressRequestDto addressDto, Long id) {
        addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        Address address = AddressMapper.toAddress(addressDto);
        address.setId(id);
        addressRepository.save(address);
        return AddressMapper.toDto(address);
    }

    @Override
    public void deleteAddress(Long id) {

    }


}
