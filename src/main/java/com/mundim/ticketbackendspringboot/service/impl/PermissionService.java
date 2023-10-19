package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.dto.request.PermissionRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.PermissionResponseDto;
import com.mundim.ticketbackendspringboot.entity.Permission;
import com.mundim.ticketbackendspringboot.exception.AlreadyExistsException;
import com.mundim.ticketbackendspringboot.exception.ResourceNotFoundException;
import com.mundim.ticketbackendspringboot.mapper.Mapper;
import com.mundim.ticketbackendspringboot.repository.PermissionRepository;
import com.mundim.ticketbackendspringboot.service.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PermissionService implements IPermissionService {
    private final PermissionRepository permissionRepository;
    @Override
    public PermissionResponseDto create(PermissionRequestDto permissionDto) {
        try{
            Permission permission = Mapper.map(permissionDto, Permission.class);
            permissionRepository.save(permission);
            return Mapper.map(permission, PermissionResponseDto.class);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw  new AlreadyExistsException(String.format("Permission name %s already registered", permissionDto.getRoleName()));
        }
    }

    @Override
    public PermissionResponseDto fetch(Integer id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permission", "id", Integer.toString(id))
        );
        return Mapper.map(permission, PermissionResponseDto.class);
    }

    @Override
    public PermissionResponseDto update(PermissionRequestDto permissionDto, Integer id) {
        permissionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permission", "id", Integer.toString(id))
        );
        Permission permission = Mapper.map(permissionDto, Permission.class);
        permission.setId(id);
        permissionRepository.save(permission);
        return Mapper.map(permission, PermissionResponseDto.class);
    }

    @Override
    public List<PermissionResponseDto> fetchAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return  Mapper.mapList(permissions, PermissionResponseDto.class);
    }

}
