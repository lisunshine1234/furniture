package com.lzy.furniture.repository;

import com.lzy.furniture.entity.VisitTotle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface VisitTotleRepository extends JpaRepository<VisitTotle,Integer> {
    VisitTotle findFirst1ByIpAndProdIdOrderByVisitTimeDesc(String ip,Integer prodId);

    VisitTotle findFirst1ByIpAndProdIdAndUserIdOrderByVisitTimeDesc(String ip, Integer prodId, Integer userId);

    @Query(value = "select DATE_FORMAT(visit_time,'%m-%d') as date,count(*) as count from visit_totle where TIMESTAMPDIFF(DAY,DATE_FORMAT(visit_time,'%Y-%m-%d'),DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) < 7 group by DATE_FORMAT(visit_time,'%Y-%m-%d') ORDER BY date",nativeQuery = true)
    List<Map<String, Object>> getWeekVisit();

    void deleteByProdId(Integer prodId);
}
