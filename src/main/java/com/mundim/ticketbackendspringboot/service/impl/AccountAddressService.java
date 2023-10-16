package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.AddressRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AddressResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.entity.Address;
import com.mundim.ticketbackendspringboot.exception.AddressAlreadyExistsException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.AccountRepository;
import com.mundim.ticketbackendspringboot.repository.AddressRepository;
import com.mundim.ticketbackendspringboot.service.IAccountAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AccountAddressService implements IAccountAddressService {
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;
    @Transactional
    @Override
    public AddressResponseDto create(AddressRequestDto addressDto, Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "id", Long.toString(id))
        );
        if(account.getAddress() != null){
            throw new AddressAlreadyExistsException("Address already registered");
        }
        Address address = Mapper.map(addressDto, Address.class);
        account.setAddress(address);
        accountRepository.save(account);
        return Mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto fetch(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        return Mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto update(AddressRequestDto addressDto, Long id) {
        addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", Long.toString(id))
        );
        Address address = Mapper.map(addressDto, Address.class);
        address.setId(id);
        addressRepository.save(address);
        return Mapper.map(address, AddressResponseDto.class);
    }

}
