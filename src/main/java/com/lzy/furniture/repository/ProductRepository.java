package com.lzy.furniture.repository;

import com.lzy.furniture.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from Product order by upload_time desc limit 0,?", nativeQuery = true)
    List<Product> findLastProductListOrderByUploadTime(Integer num);

    @Query(value = "select prod_id from visit_totle group by prod_id order by avg(TIMESTAMPDIFF(DAY,'2009-12-31 23:59:59',visit_time))/(select avg(TIMESTAMPDIFF(DAY,'2009-12-31 23:59:59',visit_time)) from visit_totle)+LOG((select count(*)/count(DISTINCT(prod_id)) from visit_totle),count(*)) desc limit 0,?", nativeQuery = true)
    List<Integer> findHotProductList(Integer num);

    @Query(value = "select prod_id from visit_totle group by prod_id order by count(*)*(TIMESTAMPDIFF(DAY,DATE_FORMAT(visit_time,'%Y-%m-%d'),CURDATE())) asc", nativeQuery = true)
    List<Integer> findPersonProductList();

    @Query(value = "select prod_id from visit_totle where user_id=? group by prod_id order by count(*)*(TIMESTAMPDIFF(DAY,DATE_FORMAT(visit_time,'%Y-%m-%d'),CURDATE())) asc limit 0,3", nativeQuery = true)
    List<Integer> findPersonProductListByUserId(Integer userId);

    @Query(value = "select prod_id from visit_totle where ip=? group by prod_id order by count(*)*(TIMESTAMPDIFF(DAY,DATE_FORMAT(visit_time,'%Y-%m-%d'),CURDATE())) asc limit 0,3", nativeQuery = true)
    List<Integer> findPersonProductListByIP(String ip);

    List<Product> findAllByFatherIdOrderByUploadTimeDesc(Integer fatherId);

    List<Product> findAllByFatherIdAndChildIdInOrderByUploadTimeDesc(Integer fatherId, List<Integer> childIdList);

    @Query(value = "select * from product where(id like %?% or prod_name like %?%) order by upload_time desc", nativeQuery = true)
    List<Product> findAllByIdContainingOrProdNameContainingOrderByUploadTimeDesc(String id, String prodName);

    @Query(value = "select * from product where child in ? and (id like %?% or  prod_name like %?%) order by upload_time desc", nativeQuery = true)
    List<Product> findAllByChildIdInAndIdContainingOrProdNameContainingOrderByUploadTimeDesc(List<Integer> childIdList,String id, String prodName);

    List<Product> findAllByIdIn(List<Integer> prodIdList);

    /**
     * 管理端
     * 获取产品浏览数量
     *
     * @return
     */
    @Query(value = "select sum(visit_count) from product", nativeQuery = true)
    Long getVisitCount();

    /**
     * 管理端
     * 获取全部家具，按照插入时间降序
     *
     * @return
     */
    @Query(value = "select * from product order by upload_time desc", nativeQuery = true)
    List<Product> findAllOrderByUploadTimeDesc();

    /**
     * 管理端
     * 按照子类，分组求和访问量
     *
     * @return
     */
    @Query(value = "select child_id,sum(visit_count) as count  from product GROUP BY child_id", nativeQuery = true)
    List<Map<String, Object>> getVisitCountGroupChildId();

    /**
     * 管理端
     * 按照父类，分组求和访问量
     *
     * @return
     */
    @Query(value = "select father_id,sum(visit_count) as count  from product GROUP BY father_id", nativeQuery = true)
    List<Map<String, Object>> getVisitCountGroupFatherId();


    List<Product> findAllByFatherId(Integer fatherId);

    List<Product> findAllByChildId(Integer childId);


}
