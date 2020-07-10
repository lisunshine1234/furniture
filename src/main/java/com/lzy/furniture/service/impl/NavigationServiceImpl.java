package com.lzy.furniture.service.impl;

import com.lzy.furniture.config.redis.RedisService;
import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.NavigationFather;
import com.lzy.furniture.repository.NavigationChildRepository;
import com.lzy.furniture.repository.NavigationFatherRepository;
import com.lzy.furniture.repository.ProductRepository;
import com.lzy.furniture.service.NavigationService;
import org.hibernate.pretty.MessageHelper;
import org.omg.CosNaming.NameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class NavigationServiceImpl implements NavigationService {
    @Autowired
    private NavigationFatherRepository navigationFatherRepository;

    @Autowired
    private NavigationChildRepository navigationChildRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<NavigationFather> getNavigationList() {
        List<NavigationFather> navigationFatherList;
        if (redisService.hasKey("furniture:navigation:all")) {
            navigationFatherList = (List<NavigationFather>) redisService.get("furniture:navigation:all");
        } else {
            navigationFatherList = navigationFatherRepository.findAll();
            redisService.set("furniture:navigation:all", navigationFatherList, 1800);
        }

        return navigationFatherList;
    }

    @Override
    public List<NavigationChild> getNavigationChildList(Integer fatherId) {
        return navigationChildRepository.findAllByFatherId(fatherId);
    }

    @Override
    public List<NavigationChild> getNavigationChildListByChildId(Set<Integer> childIdList) {
        return navigationChildRepository.findAllByIdIn(childIdList);
    }

    @Override
    public List<NavigationChild> getNavigationFatherChild() {
        List<NavigationChild> navigationChildList = navigationChildRepository.findAll();

        List<NavigationFather> navigationFatherList = navigationFatherRepository.findAll();

        List<NavigationChild> list = new ArrayList<NavigationChild>();

        for (NavigationChild navigationChild : navigationChildList) {
            for (NavigationFather navigationFather : navigationFatherList) {
                if (navigationChild.getFatherId().equals(navigationFather.getId())) {
                    navigationChild.setFatherName(navigationFather.getNaviName());
                    list.add(navigationChild);
                    break;
                }
            }
        }
        return list;
    }

    @Override
    public List<NavigationChild> getNavigationChildList() {
        return navigationChildRepository.findAll();
    }

    @Override
    public boolean addNavigationFather(NavigationFather navigationFather) {

        redisService.del("furniture:navigation:all");
        navigationFatherRepository.save(navigationFather);
        return true;
    }

    @Override
    public boolean addNavigationChild(NavigationChild navigationChild) {
        redisService.del("furniture:navigation:all");
        navigationChildRepository.save(navigationChild);
        return true;
    }

    @Override
    public boolean deleteNavigationFather(Integer fatherId) {
        if (productRepository.findAllByFatherId(fatherId).size() > 0) {
            return false;
        } else if (navigationChildRepository.findAllByFatherId(fatherId).size() > 0) {
            return false;
        } else {
            redisService.del("furniture:navigation:all");
            navigationFatherRepository.deleteById(fatherId);
            return true;
        }
    }

    @Override
    public boolean deleteNavigationChild(Integer childId) {
        if (productRepository.findAllByChildId(childId).size() > 0) {
            return false;
        } else {
            redisService.del("furniture:navigation:all");
            navigationChildRepository.deleteById(childId);
            return true;
        }
    }
}
