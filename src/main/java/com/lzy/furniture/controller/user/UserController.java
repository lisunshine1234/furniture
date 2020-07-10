package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private Integer count = 0;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/quit")
    public String quitLogin(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_name")) {
                cookie.setMaxAge(0);
                count++;
                if (count == 2) {
                    break;
                }
            }
            if (cookie.getName().equals("user_password")) {
                cookie.setMaxAge(0);
                count++;
                if (count == 2) {
                    break;
                }
            }
        }
        return "redirect:/login";

    }
}
