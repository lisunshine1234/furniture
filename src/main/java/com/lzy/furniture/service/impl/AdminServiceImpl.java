package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.Admin;
import com.lzy.furniture.repository.AdminRepository;
import com.lzy.furniture.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin adminLogin(String loginName, String password) {
        return adminRepository.findByLoginNameAndPassword(loginName,password);
    }
}
