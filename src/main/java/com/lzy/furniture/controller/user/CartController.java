package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.*;
import com.lzy.furniture.service.CartService;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    /**
     * 获取收藏夹列表，将查询的收藏夹和家具的详细信息连接
     *
     * @param request
     * @return 视图
     */
    @RequestMapping("/cart")
    public ModelAndView getCartList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return new ModelAndView("redirect:/login?url=/cart");
        } else {
            ModelAndView view = new ModelAndView();
            List<Cart> cartList = cartService.getCartList(user.getId());
            List<Integer> prodIdList = new ArrayList<Integer>();

            if (cartList != null) {
                for (Cart c : cartList) {
                    prodIdList.add(c.getProdId());
                }
                List<Product> productList = cartService.getProductList(prodIdList);

                for (Cart cart : cartList) {
                    for (Product product : productList) {
                        if (cart.getProdId().equals(product.getId())) {
                            cart.setProductInfo(product);
                            break;
                        }
                    }
                }
                view.addObject("cartList", cartList);
            }else{
                view.addObject("cartList", new ArrayList<>());
            }

            view.addObject("navigationFather", navigationService.getNavigationList());
            view.addObject("company", companyService.getCompanyInfo());
            view.addObject("contextPath", contextPath);
            view.setViewName("customer/cart");
            return view;
        }
    }

    /**
     * 删除收藏夹家具
     *
     * @param cartId
     * @param request
     * @param response
     * @return 1：删除成功 2.删除失败
     */
    @RequestMapping("/deleteCart")
    @ResponseBody
    public Integer deleteCart(@RequestParam(value = "cartId", required = false) Integer cartId,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null) {
            try {
                response.sendRedirect(contextPath+"/login?url=cart");
            } catch (Exception ignored) {

            }
        } else if (cartId == null) {
            try {
                response.sendRedirect(contextPath+"/cart");
            } catch (Exception ignored) {

            }
        } else {
            if (cartService.deleteCart(cartId)) {
                return 1;
            }
            return 2;
        }
        return 0;
    }

    /**
     * 用户添加收藏夹，检查是否重复收藏
     *
     * @param prodId
     * @param url
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/checkProduct")
    @ResponseBody
    public Integer checkProduct(@RequestParam(value = "prodId", required = false) Integer prodId,
                                @RequestParam(value = "url", required = false) String url,
                                HttpServletResponse response,
                                HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return 2;
        } else {
            if (prodId == null) {
                try {
                    response.sendRedirect(contextPath+"/product");
                } catch (Exception ignored) {
                }
            }
            if (!cartService.checkProduct(prodId, user.getId())) {
                return 1;
            }
            return 0;
        }

    }

    /**
     * 添加收藏夹
     *
     * @param prodId
     * @param url
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/addCart")
    @ResponseBody
    public Integer addCart(@RequestParam(value = "prodId", required = false) Integer prodId,
                           @RequestParam(value = "url", required = false) String url,
                           HttpServletResponse response,
                           HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return 2;
        } else {
            if (prodId == null) {
                try {
                    response.sendRedirect(contextPath+"/cart");
                } catch (Exception ignored) {
                }
            } else {
                Date date = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = format.format(date);

                Cart cart = new Cart();
                cart.setProdId(prodId);
                cart.setUserId(user.getId());
                cart.setAddTime(time);

                if (cartService.addProductToCart(cart)) {
                    return 1;
                }
            }
            return 0;
        }


    }
}
