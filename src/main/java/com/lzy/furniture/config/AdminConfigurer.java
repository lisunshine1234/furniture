package com.lzy.furniture.config;

import com.lzy.furniture.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class AdminConfigurer implements WebMvcConfigurer {

    @Autowired
    private IndexService indexService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionHandlerInterceptor())
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/doLogin")
                .excludePathPatterns("/admin/quit")
                .addPathPatterns("/admin/*");
    }

    class SessionHandlerInterceptor implements HandlerInterceptor {
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {

            if (request.getSession().getAttribute("admin") == null) {
                response.sendRedirect(contextPath+"/admin/login");
            }else{
                request.getSession().setAttribute("admin",request.getSession().getAttribute("admin"));
            }

            return true;
        }
    }
}
