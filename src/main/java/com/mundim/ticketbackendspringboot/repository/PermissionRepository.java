package com.mundim.ticketbackendspringboot.repository;

import com.mundim.ticketbackendspringboot.entity.Account;
import com.mundim.ticketbackendspringboot.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByRoleName(String roleName);
}
