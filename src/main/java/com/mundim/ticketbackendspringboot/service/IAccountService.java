package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.entity.Account;

public interface IAccountService {

    /**
     *
     * @param username - Input Account username
     * @return Account Details based on a given username
     */
    Account fetchAccountByUsername(String username);
}
