package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.repository.RegisterRepository;
import com.lzy.furniture.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public User registerUser(User user) {
        return registerRepository.save(user);
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return registerRepository.findByLoginName(loginName) != null;
    }

    @Override
    public boolean checkEmail(String email) {
        return registerRepository.findByEmail(email) != null;
    }

    @Override
    public boolean checkPhone(String phone) {
        return registerRepository.findByPhone(phone) != null;
    }

    @Override
    public boolean checkLoginNameNotUserId(String loginName, Integer userId) {

        return registerRepository.findByLoginNameAndIdNot(loginName, userId) != null;
    }

    @Override
    public boolean checkEmailNotUserId(String email, Integer userId) {
        return registerRepository.findByEmailAndIdNot(email, userId) != null;
    }

    @Override
    public boolean checkPhoneNotUserId(String phone, Integer userId) {
        return registerRepository.findByPhoneAndIdNot(phone, userId) != null;
    }

}
