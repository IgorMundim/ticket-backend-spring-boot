package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.PasswordRequestDto;
import com.mundim.ticketbackendspringboot.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class MockAccount {
    public Account mockEntity(){
        return mockEntity(0);
    }

    public List<Account> mockEntityList(){
        List<Account> accounts = new ArrayList<Account>();
        for(int i =0; i<14; i++){
            accounts.add(mockEntity(i));
        }
        return accounts;
    }


    public Account mockEntity(Integer number){
        Account account = new Account();
        account.setId(number.longValue());
        account.setUsername("UsernameTest"+number);
        account.setEmail("email@test.com");
        account.setPwd("PasswordTest"+number);
        account.setProfileImage("ImageTest"+number);
        return account;
    }
    public AccountRequestDto mockDto(Integer number){
        AccountRequestDto account = new AccountRequestDto();
        account.setUsername("UsernameTest"+number);
        account.setEmail("email@test.com");
        account.setPwd("PasswordTest"+number);
        account.setProfileImage("ImageTest"+number);
        return account;
    }
    public PasswordRequestDto mockPwdDto(Integer number){
        PasswordRequestDto account = new PasswordRequestDto();
        account.setNewPwd("PasswordTest");
        account.setOldPwd("PasswordTest"+number);
        return account;
    }
}
