package com.lzy.furniture.repository;


import com.lzy.furniture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<User, Integer> {
    User findByLoginName(String loginName);

    User findByEmail(String email);

    User findByPhone(String phone);

    User findByLoginNameAndIdNot(String loginName,Integer userId);

    User findByEmailAndIdNot(String email,Integer userId);

    User findByPhoneAndIdNot(String phone,Integer userId);
}
