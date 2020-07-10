package com.lzy.furniture.controller.admin;

import com.lzy.furniture.entity.Company;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CompanyAdminController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private IndexService indexService;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping("/admin/company")
    public ModelAndView company() {
        ModelAndView view = new ModelAndView();
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("messageNotReadCount", indexService.getMessageNotReadCount());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/company");
        return view;
    }

    @RequestMapping("/admin/saveCompany")
    @ResponseBody
    public String saveCompany(@RequestParam("compName") String compName,
                              @RequestParam("address1") String address1,
                              @RequestParam("address2") String address2,
                              @RequestParam("phone1User") String phone1User,
                              @RequestParam("phone1") String phone1,
                              @RequestParam("phone2User") String phone2User,
                              @RequestParam("phone2") String phone2,
                              @RequestParam("email") String email,
                              @RequestParam("batch") String batch,
                              @RequestParam("gaode") String gaode,
                              HttpServletRequest request) {
        Company company = new Company();
        company.setId(1);
        company.setCompName(compName);
        company.setAddress1(address1);
        company.setAddress2(address2);
        company.setPhone1User(phone1User);
        company.setPhone1(phone1);
        company.setPhone2User(phone2User);
        company.setPhone2(phone2);
        company.setEmail(email);
        company.setBatch(batch);
        company.setGaode(gaode);

        companyService.saveCompany(company);

        return "1";
    }
}
