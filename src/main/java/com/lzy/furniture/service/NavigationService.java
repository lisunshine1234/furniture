package com.lzy.furniture.service;

import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.NavigationFather;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface NavigationService {
    /**
     * 用户端
     * 管理员端
     * 获取父类列表
     * @return fatherNavigationList childNavigationList
     */
     List<NavigationFather> getNavigationList();

    /**
     * 用户端
     * 管理员端
     * 获取子类列表
     * @param fatherId
     * @return
     */
     List<NavigationChild> getNavigationChildList(Integer fatherId);

    /**
     * 用户端
     * 通过子类集合获取家具列表
     * @param childIdList
     * @return
     */
     List<NavigationChild> getNavigationChildListByChildId(Set<Integer> childIdList);

    /**
     * 管理端
     * 获取父类和子类列表
     * @return
     */
    List<NavigationChild> getNavigationFatherChild();

    /**
     * 获取子类列表
     * @return
     */
    List<NavigationChild> getNavigationChildList();

    @Transactional
    boolean addNavigationFather(NavigationFather navigationFather);
    @Transactional
    boolean addNavigationChild(NavigationChild navigationChild);
    @Transactional
    boolean deleteNavigationFather(Integer fatherId);
    @Transactional
    boolean deleteNavigationChild(Integer childId);



}
