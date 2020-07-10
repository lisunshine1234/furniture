package com.lzy.furniture.controller.admin;

import com.lzy.furniture.entity.Admin;
import com.lzy.furniture.service.AdminService;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginAdminController {
    @Autowired
    private AdminService adminService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/admin/login")
    public ModelAndView login(){
        ModelAndView view = new ModelAndView();
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/login");
        return view;
    }

    @RequestMapping(value = "/admin/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(@RequestParam("loginName") String loginName,
                          @RequestParam("password") String password,
                          HttpServletRequest request){
        Admin admin = adminService.adminLogin(loginName,password);
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
            return "ok";
        }
        return "not_ok";
    }

    @GetMapping("/admin/quit")
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/admin/login";
    }
}
