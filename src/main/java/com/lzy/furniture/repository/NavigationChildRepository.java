package com.lzy.furniture.repository;

import com.lzy.furniture.entity.NavigationChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface NavigationChildRepository extends JpaRepository<NavigationChild, Integer> {
    List<NavigationChild> findAllByFatherId(Integer fatherId);

    List<NavigationChild> findAllByIdIn(Set<Integer> childIdList);
}
