package com.lzy.furniture.service;

import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.NavigationFather;
import com.lzy.furniture.entity.VisitTotle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VisitTotleService {
    @Transactional
     boolean saveVisitTotle(VisitTotle visitTotle);

     VisitTotle getVisitTotle(String ip,Integer prodId);

    VisitTotle getVisitTotle(String ip,Integer prodId,Integer userId);

    List<NavigationChild> getVisitCountGroupChildId();

    List<NavigationFather> getVisitCountGroupFatherId();

    List<VisitTotle> getAll();

}
