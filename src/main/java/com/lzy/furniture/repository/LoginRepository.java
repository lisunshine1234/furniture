package com.lzy.furniture.repository;

import com.lzy.furniture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<User, Integer> {

    User findByLoginNameAndPassword(String login_name, String password);
}
