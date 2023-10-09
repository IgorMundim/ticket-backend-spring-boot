package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.repository.AccountRepository;
import com.mundim.ticketbackendspringboot.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {
    private final AccountRepository userRepository;

    @Override
    public Account fetchAccountByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
