package com.lzy.furniture.repository;

import com.lzy.furniture.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByLoginNameAndPassword(String loginName,String password);
}
