package com.lzy.furniture.controller.admin;

import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexAdminController {
    @Autowired
    private IndexService indexService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/admin/index")
    public ModelAndView index(){
        ModelAndView view =  new ModelAndView();
        view.addObject("userCount",indexService.getUserCount());
        view.addObject("messageCount",indexService.getMessageCount());
        view.addObject("productCount",indexService.getProductCount());
        view.addObject("visitCount",indexService.getVisitCount());
        view.addObject("messageNotReadCount",indexService.getMessageNotReadCount());
        view.addObject("dateCount",indexService.getWeekVisit());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/index");
        return view;
    }

    @GetMapping("/admin")
    public String quit(HttpServletRequest request){
        request.getSession().removeAttribute("admin");
        return "redirect:/admin/index";
    }

}
