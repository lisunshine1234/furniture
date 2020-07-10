package com.lzy.furniture.service;

import com.lzy.furniture.entity.Admin;

public interface AdminService {
    /**
     * 管理员端
     * 管理员登录
     * @param loginName
     * @param password
     * @return
     */
    Admin adminLogin(String loginName, String password);
}
