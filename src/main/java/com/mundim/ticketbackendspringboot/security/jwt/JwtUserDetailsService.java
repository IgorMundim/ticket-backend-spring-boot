package com.mundim.ticketbackendspringboot.security.jwt;

import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final IAccountService iAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountService.fetchAccountByUsername(username);
      return new JwtUserDetails(account);
    }

    public JwtToken getTokenAuthenticated(String username){
        Account account = iAccountService.fetchAccountByUsername(username);
        return JwtUtils.createToken(username, account.getPermission().getRoleName().substring("ROLE_".length()));
    }
}
