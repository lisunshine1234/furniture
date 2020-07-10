package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.LoginService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LoginService loginService;

    @RequestMapping("/register")
    public ModelAndView register(@RequestParam(value = "url", required = false) String url,
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
        view.setViewName("customer/register");
        return view;
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public String login(@RequestParam(value = "userName", required = false) String loginName,
                        @RequestParam(value = "callName", required = false) String userName,
                        @RequestParam(value = "passWord", required = false) String password,
                        @RequestParam(value = "tel", required = false) String phone,
                        @RequestParam(value = "email", required = false) String email,
                        @RequestParam(value = "question", required = false) String question,
                        @RequestParam(value = "answer", required = false) String answer,
                        HttpServletRequest request) {

        System.out.println(loginName);
        if (loginName == null || userName == null || password == null || phone == null || email == null || question == null || answer == null) {
            return "not_ok";
        }
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        User user = new User(loginName, password, userName, phone, email, question, answer);
        user.setRegisterTime(time);
        User userBack = registerService.registerUser(user);
        if (userBack != null) {
            request.getSession().setAttribute("user", userBack);
            String url = (String) request.getSession().getAttribute("url");
            if (url != null && !url.equals("") && !url.contains("login") && !url.contains("register")) {
                return url;
            } else {
                return "/index";
            }
        }

        return "not_ok";
    }

    @RequestMapping("/checkLoginName")
    @ResponseBody
    public Integer checkLoginName(@RequestParam(value = "value", required = false) String value,
                                  @RequestParam(value = "userId", required = false) Integer userId,
                                  HttpServletResponse response) {
        if (value == null) {
            try {
                response.sendRedirect(contextPath + "/register");
            } catch (Exception ignored) {
            }
        }
        if (userId == null) {
            if (!registerService.checkLoginName(value)) {
                return 1;
            }
        } else {
            if (!registerService.checkLoginNameNotUserId(value, userId)) {
                return 1;
            }
        }
        return 0;

    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public Integer checkEmail(@RequestParam(value = "value", required = false) String value,
                              @RequestParam(value = "userId", required = false) Integer userId,
                              HttpServletResponse response) {
        if (value == null) {
            try {
                response.sendRedirect(contextPath + "/register");
            } catch (Exception ignored) {
            }
        }
        if (userId == null) {
            if (!registerService.checkEmail(value)) {
                return 1;
            }
        } else {
            if (!registerService.checkEmailNotUserId(value, userId)) {
                return 1;
            }
        }

        return 0;
    }

    @RequestMapping("/checkPhone")
    @ResponseBody
    public Integer checkPhone(@RequestParam(value = "value", required = false) String value,
                              @RequestParam(value = "userId", required = false) Integer userId,
                              HttpServletResponse response) {
        if (value == null) {
            try {
                response.sendRedirect(contextPath + "/register");
            } catch (Exception ignored) {
            }
        }
        if (userId == null) {
            if (!registerService.checkPhone(value)) {
                return 1;
            }
        } else {
            if (!registerService.checkPhoneNotUserId(value, userId)) {
                return 1;
            }
        }

        return 0;
    }


}
