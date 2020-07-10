package com.lzy.furniture.controller.admin;

import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.NavigationFather;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationAdminController {
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private IndexService indexService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/admin/navigation")
    public ModelAndView navigation(){
        ModelAndView view  = new ModelAndView();

        view.addObject("navigationChildList",navigationService.getNavigationFatherChild());
        view.addObject("navigationFatherList",navigationService.getNavigationList());
        view.addObject("messageNotReadCount",indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/navigation");
        return view;
    }

    @PostMapping("/admin/addNavigationFather")
    @ResponseBody
    public String addnavigationFather(@RequestParam(value="fatherName") String fatherName,
                                      @RequestParam(value = "fatherId",required = false) Integer fatherId){
        NavigationFather navigationFather = new NavigationFather();
        if(fatherId != null){
            navigationFather.setId(fatherId);
        }
        navigationFather.setNaviName(fatherName);

        return navigationService.addNavigationFather(navigationFather)?"1":"0";
    }

    @PostMapping("/admin/addNavigationChild")
    @ResponseBody
    public String addnavigationChild(@RequestParam(value="childName") String childName,
                                     @RequestParam(value = "fatherId") Integer fatherId,
                                      @RequestParam(value = "childId",required = false) Integer childId){
        NavigationChild navigationChild = new NavigationChild();
        if(childId != null){
            navigationChild.setId(childId);
        }
        navigationChild.setFatherId(fatherId);
        navigationChild.setNaviName(childName);

        return navigationService.addNavigationChild(navigationChild)?"1":"0";
    }

    @GetMapping("/admin/deleteNavigationFather")
    @ResponseBody
    public String deleteNavigationFather(@RequestParam(value = "fatherId") Integer fatherId){
        return navigationService.deleteNavigationFather(fatherId)?"1":"0";
    }

    @GetMapping("/admin/deleteNavigationChild")
    @ResponseBody
    public String deleteNavigationChild(@RequestParam(value = "childId") Integer childId){
        return navigationService.deleteNavigationChild(childId)?"1":"0";
    }
}
