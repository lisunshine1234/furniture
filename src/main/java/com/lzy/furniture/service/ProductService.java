package com.lzy.furniture.service;

import com.lzy.furniture.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    /**
     * 获取最近更新的产品信息列表
     *
     * @param num 获取产品的条数
     * @return 产品信息List
     */
    List<Product> getLastProductList(Integer num);

    /**
     * 通过父类导航进入相应的产品列表
     *
     * @param fatherId
     * @return 产品信息List
     */
    List<Product> getProductListByFatherId(Integer fatherId);

    /**
     * 异步刷新家具列表
     *
     * @param fatherId
     * @param array
     * @return
     */
    List<Product> getProductListByFatherIdAndChildId(Integer fatherId, List<Integer> array);


    List<Product> getProductListBySearch(String prodName);

    List<Product> getProductListBySearchAndChildId(List<Integer> array, String SearchInfo);

    /**
     * 查看家具详细信息
     *
     * @param
     * @return
     */
    public Product getProductViewInfo(Integer prodId);

    @Transactional
    boolean updateProductCount(Product product);


    List<Product> getHotProductList(Integer num);

    List<Product> getPersonProductList(Integer num,Integer userId,String ip);
    /**
     * 管理端
     * 获取全部产品列表，按时间插入顺序降序
     *
     * @return
     */
    List<Product> getAllProductOrderTime();

    @Transactional
    boolean saveProduct(Product product);

    @Transactional
    boolean deleteProduct(Integer prodId);

}
