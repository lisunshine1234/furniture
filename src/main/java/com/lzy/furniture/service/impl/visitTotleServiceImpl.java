package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.NavigationFather;
import com.lzy.furniture.entity.VisitTotle;
import com.lzy.furniture.repository.NavigationChildRepository;
import com.lzy.furniture.repository.NavigationFatherRepository;
import com.lzy.furniture.repository.ProductRepository;
import com.lzy.furniture.repository.VisitTotleRepository;
import com.lzy.furniture.service.VisitTotleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class visitTotleServiceImpl implements VisitTotleService {
    @Autowired
    private VisitTotleRepository visitTotleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NavigationFatherRepository navigationFatherRepository;

    @Autowired
    private NavigationChildRepository navigationChildRepository;


    @Override
    public boolean saveVisitTotle(VisitTotle visitTotle) {
        visitTotleRepository.save(visitTotle);
        return true;
    }

    @Override
    public VisitTotle getVisitTotle(String ip,Integer prodId) {
        return visitTotleRepository.findFirst1ByIpAndProdIdOrderByVisitTimeDesc(ip,prodId);
    }

    @Override
    public VisitTotle getVisitTotle(String ip, Integer prodId, Integer userId) {
        return visitTotleRepository.findFirst1ByIpAndProdIdAndUserIdOrderByVisitTimeDesc(ip,prodId,userId);
    }


    @Override
    public List<NavigationChild> getVisitCountGroupChildId() {
        List<Map<String,Object>> list = productRepository.getVisitCountGroupChildId();


        List<NavigationChild> list1 = navigationChildRepository.findAll();

        List<NavigationChild> navigationChildList = new ArrayList<NavigationChild>();

        for(NavigationChild navigationChild : list1){
            for(Map<String,Object> map : list){
                if(((Integer) map.get("child_id")).equals(navigationChild.getId())){
                    navigationChild.setCount(Integer.parseInt(map.get("count").toString()));
                    break;
                }
            }
            navigationChildList.add(navigationChild);
        }

        return navigationChildList;
    }

    @Override
    public List<NavigationFather> getVisitCountGroupFatherId() {
        List<Map<String,Object>> list = productRepository.getVisitCountGroupFatherId();


        List<NavigationFather> list1 = navigationFatherRepository.findAll();

        List<NavigationFather> navigationFatherList = new ArrayList<NavigationFather>();

        for(NavigationFather navigationFather : list1){
            for(Map<String,Object> map : list){
                if(((Integer) map.get("father_id")).equals(navigationFather.getId())){
                    navigationFather.setCount(Integer.parseInt(map.get("count").toString()));
                    break;
                }
            }
            navigationFatherList.add(navigationFather);
        }

        return navigationFatherList;
    }

    @Override
    public List<VisitTotle> getAll() {
        return visitTotleRepository.findAll();
    }
}
