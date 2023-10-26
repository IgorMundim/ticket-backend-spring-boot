package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class MockAccount {
    public Account mockEntity(){
        return mockEntity(0);
    }
    public AccountRequestDto mockAccountDto(){
        return mockDto(0);
    }

    public List<Account> mockEntityList(){
        List<Account> accounts = new ArrayList<Account>();
        for(int i =0; i<14; i++){
            accounts.add(mockEntity(i));
        }
        return accounts;
    }

    public List<AccountRequestDto> mockDtoList(){
        List<AccountRequestDto> accounts = new ArrayList<AccountRequestDto>();
        for(int i =0; i<14; i++){
            accounts.add(mockDto(i));
        }
        return accounts;
    }
    public Account mockEntity(Integer number){
        Account account = new Account();
        account.setId(number.longValue());
        account.setUsername("UsernameTest"+number);
        account.setEmail("EmailTest"+number);
        account.setPwd("PasswordTest"+number);
        account.setProfileImage("ImageTest"+number);
        return account;
    }
    public AccountRequestDto mockDto(Integer number){
        AccountRequestDto account = new AccountRequestDto();
        account.setUsername("UsernameTest"+number);
        account.setEmail("EmailTest"+number);
        account.setPwd("PasswordTest"+number);
        account.setProfileImage("ImageTest"+number);
        return account;
    }
}
