package com.mundim.ticketbackendspringboot.security.jwt;

import com.mundim.ticketbackendspringboot.entity.Account;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails  extends User {
    private Account account;

    public JwtUserDetails(Account account){
        super(account.getUsername(), account.getPwd(), AuthorityUtils.createAuthorityList(account.getPermission().getRoleName()));
        this.account = account;
    }

    public Long getId() {
        return this.account.getId();
    }
    public String getRole() {
        return this.account.getPermission().getRoleName();
    }
}
