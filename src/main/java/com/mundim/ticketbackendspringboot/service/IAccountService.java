package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;

public interface IAccountService {

    /**
     *
     * @param accountDto - AccountDto Object
     */
    AccountResponseDto create(AccountRequestDto accountDto);

    /**
     *
     * @param accountDto - AccountDto Object
     */
    AccountResponseDto createAdmin(AccountRequestDto accountDto);


    /**
     *
     * @param username - Input Account username
     * @return Account Details based on a given username
     */
    Account fetchAccountByUsername(String username);

    /**
     *
     * @param id - Input Account id
     * @return Account Details base on a given id
     */
    AccountResponseDto fetchById(Long id);
}
