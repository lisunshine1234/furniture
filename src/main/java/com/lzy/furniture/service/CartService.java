package com.lzy.furniture.service;

import com.lzy.furniture.entity.Cart;
import com.lzy.furniture.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartService {
    /**
     * 用户端
     * 通过用户ID获取收藏夹列表，包含家具ID，但是不包括家具详细信息
     * @param userId
     * @return
     */
     List<Cart> getCartList(Integer userId);

    /**
     * 用户端
     * 通过getCartList函数得到家具ID集合，通过集合查询到家具的详细信息
     * @param prodId
     * @return
     */
     List<Product> getProductList(List<Integer> prodId);

    /**
     * 用户端
     * 删除收藏夹的信息
     * @param cartId
     * @return
     */
    @Transactional
     boolean deleteCart(Integer cartId);

    /**
     * 用户端
     * 查看收藏夹中是否已经收藏过家具
     * @param prodId
     * @param userId
     * @return
     */
    boolean checkProduct(Integer prodId,Integer userId);

    /**
     * 用户端
     * 添加家具到收藏夹
     * @param cart
     * @return
     */
    @Transactional
     boolean addProductToCart(Cart cart);

}
