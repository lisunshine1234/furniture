package com.lzy.furniture.repository;

import com.lzy.furniture.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserIdOrderByAddTimeDesc(Integer userId);

    Cart findByProdIdAndUserId(Integer prodId, Integer userId);

    void deleteByProdId(Integer prodId);
}
