package com.lzy.furniture.service;

import com.lzy.furniture.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> getUserAllList();
    @Transactional
    boolean deleteUser(Integer userId);

    User findUser(Integer userId);
}
