package com.axa.axatest.services.impl;

import com.axa.axatest.entity.User;
import com.axa.axatest.repository.UserRepository;
import com.axa.axatest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;




    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
