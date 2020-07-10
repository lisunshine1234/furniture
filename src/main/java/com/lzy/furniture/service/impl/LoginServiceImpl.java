package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.repository.LoginRepository;
import com.lzy.furniture.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public User login(String user_name, String user_password) {
        return loginRepository.findByLoginNameAndPassword(user_name,user_password);
    }
}
