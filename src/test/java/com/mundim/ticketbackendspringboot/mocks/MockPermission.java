package com.mundim.ticketbackendspringboot.mocks;

import com.mundim.ticketbackendspringboot.dto.request.PermissionRequestDto;
import com.mundim.ticketbackendspringboot.entity.Permission;

import java.util.ArrayList;
import java.util.List;

public class MockPermission {
    public Permission mockEntity(){
        return mockEntity(0);
    }
    public PermissionRequestDto mockAccountDto(){
        return mockDto(0);
    }

    public List<Permission> mockEntityList(){
        List<Permission> entities = new ArrayList<Permission>();
        for(int i =0; i<14; i++){
            entities.add(mockEntity(i));
        }
        return entities;
    }

    public List<PermissionRequestDto> mockDtoList(){
        List<PermissionRequestDto> entities = new ArrayList<PermissionRequestDto>();
        for(int i =0; i<14; i++){
            entities.add(mockDto(i));
        }
        return entities;
    }
    public Permission mockEntity(Integer number){
        Permission entity = new Permission();
        entity.setId(number);
        entity.setRoleName("ROLE_"+number);
        return entity;
    }
    public Permission mockEntity(Integer number, String name){
        Permission entity = new Permission();
        entity.setId(number);
        entity.setRoleName("ROLE_"+name);
        return entity;
    }
    public PermissionRequestDto mockDto(Integer number){
        PermissionRequestDto entity = new PermissionRequestDto();
        entity.setRoleName("ROLE_"+number);
        return entity;
    }
}
