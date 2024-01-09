package com.axa.axatest.services;

import com.axa.axatest.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();
    User create(User user);
    Optional<User> findUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
