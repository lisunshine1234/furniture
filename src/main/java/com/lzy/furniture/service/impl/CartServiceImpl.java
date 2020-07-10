package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.Cart;
import com.lzy.furniture.entity.Product;
import com.lzy.furniture.repository.CartRepository;
import com.lzy.furniture.repository.ProductRepository;
import com.lzy.furniture.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Cart> getCartList(Integer userId) {
        return cartRepository.findAllByUserIdOrderByAddTimeDesc(userId);
    }

    @Override
    public List<Product> getProductList(List<Integer> prodIdList) {
        return productRepository.findAllByIdIn(prodIdList);
    }

    @Override
    public boolean deleteCart(Integer cartId) {
        cartRepository.deleteById(cartId);
        return cartRepository.findById(cartId).orElse(null) == null;
    }

    @Override
    public boolean checkProduct(Integer prodId,Integer userId) {
        return cartRepository.findByProdIdAndUserId(prodId, userId) != null;
    }

    @Override
    public boolean addProductToCart(Cart cart) {
        cartRepository.save(cart);
        return true;
    }
}
