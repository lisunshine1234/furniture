package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.NewsService;
import com.lzy.furniture.service.ProductService;
import com.lzy.furniture.util.GetIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Value("${ProductNumber}")
    private Integer num;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private ProductService productService;

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("/index")
    public ModelAndView getLastProductList(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");

        GetIp getIp = new GetIp();
        String ip = getIp.getIp(request);

        ModelAndView view = new ModelAndView();
        view.addObject("News", newsService.getNewsIsState());
        if(user == null){
            view.addObject("PersonProductList",productService.getPersonProductList(num,null,ip));
        }else{
            view.addObject("PersonProductList",productService.getPersonProductList(num,user.getId(),ip));
        }
        view.addObject("HotProductList",productService.getHotProductList(num));
        view.addObject("LastProductList", productService.getLastProductList(num));
        view.addObject("navigationFather", navigationService.getNavigationList());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("customer/index");
        return view;
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/index";
    }

}
