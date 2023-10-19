package com.mundim.ticketbackendspringboot.service;

import com.mundim.ticketbackendspringboot.dto.request.PermissionRequestDto;
import com.mundim.ticketbackendspringboot.dto.response.PermissionResponseDto;

import java.util.List;

public interface IPermissionService {
    /**
     *
     * @param permissionDto - PermissionResponseDto Object
     */
    PermissionResponseDto create(PermissionRequestDto permissionDto);

    /**
     *
     * @param id - Input Permission ID
     * @return Permission Details based on a given id
     */
    PermissionResponseDto fetch(Integer id);

    /**
     *
     * @param permissionDto - PermissionDto Object
     * @return boolean indicating if the update of Permission details is successful or not
     */
    PermissionResponseDto update(PermissionRequestDto permissionDto, Integer id);

    /**
     *
     * @param id - Permission id
     * @return - All permission by id
     */
    List<PermissionResponseDto> fetchAll();
}
