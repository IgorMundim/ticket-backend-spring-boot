package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.entity.Address;
import com.mundim.ticketbackendspringboot.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    @Transactional
    public void create (Address address){
           addressRepository.save(address);
    }
}
