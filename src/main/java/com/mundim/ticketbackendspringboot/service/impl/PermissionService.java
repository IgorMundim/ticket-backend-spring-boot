package com.mundim.ticketbackendspringboot.service.impl;

import com.mundim.ticketbackendspringboot.controller.PermissionController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class PermissionService implements IPermissionService {
    private final PermissionRepository permissionRepository;
    @Override
    public PermissionResponseDto create(PermissionRequestDto permissionDto) {
        Permission permission = Mapper.map(permissionDto, Permission.class);
        if(permissionRepository.findByRoleName(permission.getRoleName()).isPresent()){
            throw new AlreadyExistsException(
                    String
                    .format("Permission name %s already registered",
                            permissionDto.getRoleName())
            );
        }

        permissionRepository.save(permission);
        return Mapper.map(permission, PermissionResponseDto.class)
                .add(linkTo(methodOn(PermissionController.class).getById(permission.getId())).withSelfRel());
    }

    @Override
    public PermissionResponseDto fetch(Integer id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permission", "id", Integer.toString(id))
        );
        return Mapper.map(permission, PermissionResponseDto.class)
                .add(linkTo(methodOn(PermissionController.class).getById(permission.getId())).withSelfRel());
    }

    @Override
    public PermissionResponseDto update(PermissionRequestDto permissionDto, Integer id) {
        Permission oldPermission = permissionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Permission", "id", Integer.toString(id))
        );
        try{
            Permission permission = Mapper.map(permissionDto, Permission.class);
            oldPermission.setRoleName(permission.getRoleName());
            permissionRepository.save(oldPermission);
            return Mapper.map(oldPermission, PermissionResponseDto.class)
                    .add(linkTo(methodOn(PermissionController.class).getById(id)).withSelfRel());
        }catch (Exception ex){
            throw new AlreadyExistsException(
                    String
                            .format("Permission name %s already registered",
                                    permissionDto.getRoleName())
            );
        }

    }

    @Override
    public List<PermissionResponseDto> fetchAll() {
        List<PermissionResponseDto> permissions = Mapper.mapList(permissionRepository.findAll(), PermissionResponseDto.class);
        permissions.forEach(p -> Mapper.map(p, PermissionResponseDto.class)
                .add(linkTo(methodOn(PermissionController.class).getById(p.getId())).withSelfRel()));
        return  Mapper.mapList(permissions, PermissionResponseDto.class);
    }

}
