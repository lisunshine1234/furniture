package com.lzy.furniture.service;

import com.lzy.furniture.entity.User;

public interface LoginService {
    /**
     * 登陆
     *
     * @param user_name     用户名
     * @param user_password 密码
     * @return 成功返回User对象
     */
    User login(String user_name, String user_password);
}
