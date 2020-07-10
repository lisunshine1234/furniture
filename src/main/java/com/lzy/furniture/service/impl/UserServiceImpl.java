package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.repository.UserRepository;
import com.lzy.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getUserAllList() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Integer userId) {
        User user = new User();
        user.setId(userId);

        userRepository.delete(user);

        return true;
    }

    @Override
    public User findUser(Integer userId) {
        return userRepository.findById(userId).orElse(new User());
    }
}
