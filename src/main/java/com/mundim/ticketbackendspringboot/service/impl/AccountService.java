package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.controller.AccountController;
import com.mundim.ticketbackendspringboot.controller.PermissionController;
import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.PasswordRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.entity.Permission;
import com.mundim.ticketbackendspringboot.exception.PasswordInvalidException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.exception.UsernameUniqueViolationException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.AccountRepository;
import com.mundim.ticketbackendspringboot.repository.PermissionRepository;
import com.mundim.ticketbackendspringboot.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountResponseDto create(AccountRequestDto accountDto) {
        Permission permission = permissionRepository.findByRoleName("ROLE_CUSTOMER").orElseThrow(
                () -> new ResourceNotFoundException("Permission", "roleName", "ROLE_CUSTOMER")
        );
        try {

            Account account = Mapper.map(accountDto, Account.class);
            account.setPwd(passwordEncoder.encode(account.getPwd()));
            account.setPermission(permission);
            accountRepository.save(account);
            return Mapper.map(account, AccountResponseDto.class)
                    .add(linkTo(methodOn(AccountController.class).getById(account.getId())).withSelfRel())
                    .add(linkTo(methodOn(PermissionController.class).getById(permission.getId())).withSelfRel());
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new UsernameUniqueViolationException(String.format("Username %s already registered", accountDto.getUsername()));
        }
    }
    @Override
    public AccountResponseDto createAdmin(AccountRequestDto accountDto) {
        Permission permission = permissionRepository.findByRoleName("ROLE_ADMIN").orElseThrow(
                () ->  new ResourceNotFoundException("Permission","roleName", "ROLE_ADMIN")
        );
        try {
            Account account = Mapper.map(accountDto, Account.class);
            account.setPwd(passwordEncoder.encode(account.getPwd()));
            account.setPermission(permission);
            accountRepository.save(account);
            return Mapper.map(account, AccountResponseDto.class)
                    .add(linkTo(methodOn(AccountController.class).getById(account.getId())).withSelfRel())
                    .add(linkTo(methodOn(PermissionController.class).getById(permission.getId())).withSelfRel());
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new UsernameUniqueViolationException(String.format("Username %s already registered", accountDto.getUsername()));
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
        return  Mapper.map(account, AccountResponseDto.class)
                .add(linkTo(methodOn(AccountController.class).getById(account.getId())).withSelfRel());
    }

    @Override
    public Void updatePassword(Long id, PasswordRequestDto passwordDto) {
        if(passwordDto.getNewPwd().equals(passwordDto.getOldPwd())){
            throw new PasswordInvalidException("newPwd is the same as the oldPwd.");
        }
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Account", "id", Long.toString(id))
        );
        if(!passwordEncoder.matches(passwordDto.getOldPwd(), account.getPwd())){
            throw new PasswordInvalidException("oldPwd is invalid.");
        }
        account.setPwd(passwordEncoder.encode(passwordDto.getNewPwd()));
        accountRepository.save(account);
        return null;
    }


}
