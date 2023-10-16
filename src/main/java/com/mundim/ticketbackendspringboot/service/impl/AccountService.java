package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.entity.Permission;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.exception.UsernameUniqueViolationException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.AccountRepository;
import com.mundim.ticketbackendspringboot.repository.PermissionRepository;
import com.mundim.ticketbackendspringboot.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountResponseDto create(AccountRequestDto accountDto) {
        try {
            Permission permission = permissionRepository.findByRoleName("ROLE_CUSTOMER");
            Account account = Mapper.map(accountDto, Account.class);
            account.setPwd(passwordEncoder.encode(account.getPwd()));
            account.setPermission(permission);
            accountRepository.save(account);
            return Mapper.map(account, AccountResponseDto.class);
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new UsernameUniqueViolationException(String.format("Username %s", accountDto.getUsername()));
        }
    }
    @Override
    public AccountResponseDto createAdmin(AccountRequestDto accountDto) {
        try {
            Permission permission = permissionRepository.findByRoleName("ROLE_ADMIN");
            Account account = Mapper.map(accountDto, Account.class);
            account.setPwd(passwordEncoder.encode(account.getPwd()));
            account.setPermission(permission);
            accountRepository.save(account);
            return Mapper.map(account, AccountResponseDto.class);
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new UsernameUniqueViolationException(String.format("Username %s", accountDto.getUsername()));
        }
    }
    @Override
    public Account fetchAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public AccountResponseDto fetchById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "id", Long.toString(id))
        );
        return  Mapper.map(account, AccountResponseDto.class);
    }

}
