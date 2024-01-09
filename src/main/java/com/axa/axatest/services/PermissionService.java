package com.axa.axatest.services;

import com.axa.axatest.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<Permission> findAllPermission();
    Permission create(Permission permission);
    Optional<Permission> findPermissionById(Long id);

    Permission updatePermission(Permission permission);

    void deletePermission(Long id);
}
