package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.PermissionRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.AccountResponseDto;
import com.mundim.ticketbackendspringboot.dto.response.PermissionResponseDto;
import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.entity.Permission;
import com.mundim.ticketbackendspringboot.exception.AlreadyExistsException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mocks.MockPermission;
import com.mundim.ticketbackendspringboot.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PermissionServiceTest {
    MockPermission input;
    @InjectMocks
    PermissionService service;

    @Mock
    PermissionRepository permissionRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPermission();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should not accept duplicate roleName.")
    void testWillThrowAlreadyExistsException(){
        Permission entity = input.mockEntity(1);
        PermissionRequestDto entityDto = input.mockDto(1);
        when(permissionRepository.findByRoleName(entity.getRoleName())).thenReturn(
                Optional.of(entity)
        );
        Exception exception = assertThrows(AlreadyExistsException.class, () -> { service.create(entityDto); });
        String expectedMessage = "Permission name "+entityDto.getRoleName()+" already registered";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found 'Permission' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryGetPermissionById(){
        Permission entity = input.mockEntity(1);
        PermissionRequestDto entityDto = input.mockDto(1);
        when(permissionRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.fetch(entity.getId()); });
        String expectedMessage = "Permission not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    @DisplayName("It should not found 'Permission' with invalid id.")
    void testWillThrowResourceNotFoundExceptionWhenTryUpdatePermissionById(){
        Permission entity = input.mockEntity(1);
        PermissionRequestDto entityDto = input.mockDto(1);
        when(permissionRepository.findById(entity.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> { service.update( entityDto,entity.getId()); });
        String expectedMessage = "Permission not found with the given input data id : '1'";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    @DisplayName("It should not accept duplicate roleName")
    void testWillCreatePermission(){
        Permission entity = input.mockEntity(1);
        PermissionRequestDto entityDto = input.mockDto(1);
        when(permissionRepository.findByRoleName(entity.getRoleName())).thenReturn(
                Optional.empty()
        );
        PermissionResponseDto result = service.create(entityDto);
        assertNotNull(result);
        assertEquals(PermissionResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/permission/0>;rel=\"self\""));
        assertEquals("ROLE_1", result.getRoleName());
    }

    @Test
    @DisplayName("It should get an 'Permission' by account id")
    void testFetchPermissionById(){
        Permission entity = input.mockEntity(1);
        when(permissionRepository.findById(entity.getId()))
                .thenReturn(Optional.of(entity));
        var result = service.fetch(entity.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(PermissionResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/permission/1>;rel=\"self\""));
        assertEquals("ROLE_1", result.getRoleName());
    }
    @Test
    @DisplayName("It should update 'Permission'")
    void testWillUpdatePermission(){
        Permission oldEntity = input.mockEntity(1);

        PermissionRequestDto entityDto = input.mockDto(2);
        when(permissionRepository.findById(oldEntity.getId())).thenReturn(
                Optional.of(oldEntity)
        );

        PermissionResponseDto result = service.update(entityDto, 1);
        assertNotNull(result);
        assertEquals(PermissionResponseDto.class, result.getClass());
        assertTrue(result.getLinks().toString().contains("</api/v1/permission/1>;rel=\"self\""));
        assertEquals("ROLE_2", result.getRoleName());
    }
    @Test
    @DisplayName("It should get all 'Permission'")
    void testFetchAllPermission(){
        List<Permission> entities = input.mockEntityList();
        when(permissionRepository.findAll())
                .thenReturn(entities);
        List<PermissionResponseDto> result = service.fetchAll();
        PermissionResponseDto firstPermission = result.get(1);
        assertNotNull(result);
        assertEquals(PermissionResponseDto.class, firstPermission.getClass());
        assertTrue(firstPermission.getLinks().toString().contains("</api/v1/permission/1>;rel=\"self\""));
        assertEquals("ROLE_1", firstPermission.getRoleName());
    }
}