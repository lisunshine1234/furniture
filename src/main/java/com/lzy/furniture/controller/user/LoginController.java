package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.LoginService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "url", required = false) String url,
                              HttpServletRequest request) {
        if (url != null) {
            if (!url.contains("Login") && !url.contains("login") && !url.contains("register") && !url.contains("Register")) {
                request.getSession().setAttribute("url", url);
            }
        }
        ModelAndView view = new ModelAndView();
        view.addObject("navigationFather", navigationService.getNavigationList());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("customer/login");
        return view;
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "userName", required = false) String user_name,
                        @RequestParam(value = "passWord", required = false) String user_password,
                        HttpServletRequest request) {

        if (user_name == null || user_password == null) {
            return "not_ok";
        }
        User user = loginService.login(user_name, user_password);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            String url = (String) request.getSession().getAttribute("url");
            if (url != null && !url.equals("") && !url.contains("login") && !url.contains("register")) {
                return url;
            } else {
                return "/index";
            }
        } else {
            return "not_ok";
        }
    }
}
