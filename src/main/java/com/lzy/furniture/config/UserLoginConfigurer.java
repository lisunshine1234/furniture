package com.lzy.furniture.config;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Configuration
public class UserLoginConfigurer implements WebMvcConfigurer {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private LoginService loginService;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionHandlerInterceptor())
                .excludePathPatterns("/login")
                .excludePathPatterns("/doLogin")
                .excludePathPatterns("/quit")
                .excludePathPatterns("/register")
                .excludePathPatterns("/doRegister")
                .excludePathPatterns("/checkLoginName")
                .excludePathPatterns("/checkEmail")
                .excludePathPatterns("/checkPhone")
                .addPathPatterns("/*");
    }
    class SessionHandlerInterceptor implements HandlerInterceptor {
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
//            if (request.getSession().getAttribute("user") != null) {
//                User user = (User) request.getSession().getAttribute("user");
//
//                user = loginService.login(user.getLoginName(), user.getPassword());
//
//                if(user == null){
//                    request.getSession().removeAttribute("user");
//                }else{
//                    request.getSession().setAttribute("user",user);
//                }
//            }

            return true;
        }
    }
}
