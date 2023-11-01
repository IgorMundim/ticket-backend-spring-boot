package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.AccountRequestDto;
import com.mundim.ticketbackendspringboot.dto.request.PasswordRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.entity.Permission;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockAccount;
import com.mundim.ticketbackendspringboot.mocks.MockPermission;
import com.mundim.ticketbackendspringboot.repository.AccountRepository;
import com.mundim.ticketbackendspringboot.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class AccountServiceTest {
    MockAccount accountInput;
    MockPermission permissionInput;
    @InjectMocks
    private AccountService service;

    @Mock
    AccountRepository accountRepository;
    @Mock
    PermissionRepository permissionRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @BeforeEach
    void setUpMocks() throws Exception {
        accountInput = new MockAccount();
        permissionInput = new MockPermission();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should not found 'ROLE_CUSTOMER' when is trying to create a new Account.")
    void  testWillThrowPermissionNotFound(){
        String roleName = "ROLE_CUSTOMER";
        AccountRequestDto entityDto = accountInput.mockDto(1);
        when(permissionRepository.findByRoleName(roleName)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.create(entityDto); });
        String expectedMessage = "Permission not found with the given input data roleName : 'ROLE_CUSTOMER'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found 'Account' with invalid id.")
    void  testWillThrowResourceNotFoundExceptionWhenTryGetAccountById(){
        when(accountRepository.findById(1L))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetchById(1L); });
        String expectedMessage = "Account not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should not found 'Account' with invalid id.")
    void  testWillThrowResourceNotFoundExceptionWhenTryUpdateAccountById(){
        PasswordRequestDto pwdEntity =  accountInput.mockPwdDto(1);
        when(accountRepository.findById(1L))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.updatePassword(1L, pwdEntity); });
        String expectedMessage = "Account not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found 'ROLE_ADMIN' when is trying to create a new Account.")
    void  testWillThrowAdminPermissionNotFound(){
        String roleName = "ROLE_ADMIN";
        AccountRequestDto entityDto = accountInput.mockDto(1);
        when(permissionRepository.findByRoleName(roleName)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.createAdmin(entityDto); });
        String expectedMessage = "Permission not found with the given input data roleName : 'ROLE_ADMIN'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should an Account customer")
    void testWillCreateAccount(){
        String roleName = "ROLE_CUSTOMER";
        Account entity = accountInput.mockEntity(1);
        Permission entityPermission = permissionInput.mockEntity(1, roleName);
        AccountRequestDto entityDto = accountInput.mockDto(1);
        when(passwordEncoder.encode(entity.getPwd())).thenReturn("encodePwd");
        when(permissionRepository.findByRoleName(roleName)).thenReturn(Optional.of(entityPermission));
        when(accountRepository.save(entity))
                .thenReturn(entity);

        AccountResponseDto result = service.create(entityDto);
        assertNotNull(result);
        assertNull(result.getId());
        assertEquals(AccountResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/account/{id}>;rel=\"self\",</api/v1/permission/1>;rel=\"self\""));
        assertEquals("UsernameTest1", result.getUsername());
        assertEquals("email@test.com", result.getEmail());
        assertEquals("ImageTest1", result.getProfileImage());

    }
    @Test
    @DisplayName("It should an Account admin")
    void testCreateAccountAdmin(){
        String roleName = "ROLE_ADMIN";
        Account entity = accountInput.mockEntity(1);
        Permission entityPermission = permissionInput.mockEntity(1, roleName);
        AccountRequestDto entityDto = accountInput.mockDto(1);
        when(passwordEncoder.encode(entity.getPwd())).thenReturn("encodePwd");
        when(permissionRepository.findByRoleName(roleName)).thenReturn(Optional.of(entityPermission));

        AccountResponseDto result = service.createAdmin(entityDto);
        assertNotNull(result);
        assertNull(result.getId());
        assertEquals(AccountResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/account/{id}>;rel=\"self\",</api/v1/permission/1>;rel=\"self\""));
        assertEquals("UsernameTest1", result.getUsername());
        assertEquals("email@test.com", result.getEmail());
        assertEquals("ImageTest1", result.getProfileImage());

    }
    @Test
    @DisplayName("It should get an 'Account' by account username")
    void testFetchAccountByUsername(){
        Account entity = accountInput.mockEntity(1);
        when(accountRepository.findByUsername("UsernameTest1"))
                .thenReturn(entity);
        var result = service.fetchAccountByUsername("UsernameTest1");
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(Account.class, result.getClass());
        assertEquals("UsernameTest1", result.getUsername());
        assertEquals("email@test.com", result.getEmail());
        assertEquals("ImageTest1", result.getProfileImage());
    }
    @Test
    @DisplayName("It should get an 'AccountResponseDto' by account id")
    void testFindAccountById(){
        Account entity = accountInput.mockEntity(1);
        when(accountRepository.findById(1L))
                .thenReturn((Optional.of(entity)));
        var result = service.fetchById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals(AccountResponseDto.class,result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/account/1>;rel=\"self\""));
        assertEquals(1L, result.getId());
        assertEquals("UsernameTest1", result.getUsername());
        assertEquals("email@test.com", result.getEmail());
        assertEquals("ImageTest1", result.getProfileImage());
    }
    @Test
    @DisplayName("It should update an 'Account password'")
    void testUpdateAccountPassword(){
        Account entity = accountInput.mockEntity(1);
        PasswordRequestDto pwdEntity =  accountInput.mockPwdDto(1);
        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(entity));
        when(passwordEncoder.matches(pwdEntity.getOldPwd(), entity.getPwd())).thenReturn(true);
        var result = service.updatePassword(entity.getId(), pwdEntity);
        assertNull(result);
    }

}