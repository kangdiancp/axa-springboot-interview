package com.axa.axatest.services;

import com.axa.axatest.entity.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {
    List<Roles> findAllRoles();
    Roles create(Roles roles);
    Optional<Roles> findRolesById(Long id);

    Roles updateRoles(Roles roles);

    void deleteRoles(Long id);
}
