package com.lzy.furniture.repository;

import com.lzy.furniture.entity.Company;
import com.lzy.furniture.entity.NavigationFather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavigationFatherRepository extends JpaRepository<NavigationFather,Integer> {
}
