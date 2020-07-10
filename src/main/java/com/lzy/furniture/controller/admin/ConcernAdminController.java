package com.lzy.furniture.controller.admin;

import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.VisitTotleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConcernAdminController {
    @Autowired
    private VisitTotleService visitTotleService;
    @Autowired
    private IndexService indexService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping("/admin/concern")
    public ModelAndView concern(){
        ModelAndView view = new ModelAndView();
        view.addObject("navigationChildList",visitTotleService.getVisitCountGroupChildId());
        view.addObject("navigationFatherList",visitTotleService.getVisitCountGroupFatherId());
        view.addObject("messageNotReadCount",indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/concern");
        return view;
    }
}
