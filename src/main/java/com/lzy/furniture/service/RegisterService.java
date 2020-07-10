package com.lzy.furniture.service;


import com.lzy.furniture.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface RegisterService {
    @Transactional
    User registerUser(User user);

     boolean checkLoginName(String loginName);

     boolean checkEmail(String email);

     boolean checkPhone(String phone);

     boolean checkLoginNameNotUserId(String loginName,Integer userId);

     boolean checkEmailNotUserId(String email,Integer userId);

     boolean checkPhoneNotUserId(String phone,Integer userId);
}
