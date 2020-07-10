package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.Product;
import com.lzy.furniture.entity.User;
import com.lzy.furniture.entity.VisitTotle;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.ProductService;
import com.lzy.furniture.service.VisitTotleService;
import com.lzy.furniture.util.GetIp;
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
import java.util.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;

    @Autowired
    private VisitTotleService visitTotleService;

    @Autowired
    private CompanyService companyService;
    /**
     * 父导航查询家具列表
     *
     * @param fatherId
     * @return
     */
    @RequestMapping("/product")
    public ModelAndView getProductListByFatherId(@RequestParam(value = "fatherId", required = false, defaultValue = "1") Integer fatherId,
                                                 @RequestParam(value = "SearchInfo", required = false, defaultValue = "") String SearchInfo) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", productService.getProductListByFatherId(fatherId));
        view.addObject("navigationChildList", navigationService.getNavigationChildList(fatherId));
        view.addObject("fatherId", fatherId);
        view.addObject("navigationFather", navigationService.getNavigationList());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("customer/product");
        return view;
    }

    @RequestMapping("/productSearch")
    public ModelAndView getProductListByFatherId(@RequestParam(value = "SearchInfo", required = false, defaultValue = "") String SearchInfo) {
        ModelAndView view = new ModelAndView();
        List<Product> productList = productService.getProductListBySearch(SearchInfo);
        view.addObject("productList", productList);

        if (productList.size() > 0) {
            Set<Integer> childIdList = new HashSet<Integer>();
            for (Product p : productList) {
                childIdList.add(p.getChildId());
            }


            view.addObject("navigationChildList", navigationService.getNavigationChildListByChildId(childIdList));
        } else {
            view.addObject("navigationChildList", new ArrayList<>());
        }

        view.addObject("SearchInfo", SearchInfo);
        view.addObject("navigationFather", navigationService.getNavigationList());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("customer/product");
        return view;
    }

    /**
     * 子导航查询家具列表
     *
     * @param fatherId
     * @param array
     * @param response
     * @return
     */
    @RequestMapping("/productCheck")
    @ResponseBody
    public List<Product> getProductListByFatherIdAndChildId(@RequestParam(value = "fatherId", required = false, defaultValue = "1") Integer fatherId,
                                                            @RequestParam(value = "array[]", required = false, defaultValue = "[]") Integer[] array,
                                                            @RequestParam(value = "SearchInfo", required = false, defaultValue = "") String SearchInfo, HttpServletResponse response) {
        if (array[0] == -1) {
            try {
                response.sendRedirect(contextPath+"/product");
            } catch (Exception ignored) {
            }
        }
        List<Integer> list = Arrays.asList(array);
        if (SearchInfo.equals("")) {
            return productService.getProductListByFatherIdAndChildId(fatherId, list);
        } else {
            return productService.getProductListBySearchAndChildId(list, SearchInfo);
        }

    }


    /**
     * 查看家具详细信息
     *
     * @param prodId
     * @return
     */
    @RequestMapping("/productView")
    public ModelAndView getProductView(@RequestParam(value = "prodId", required = false) Integer prodId,
                                       HttpServletRequest request) {
        if (prodId == null) {
            return new ModelAndView("/product");
        } else {
            Product product = productService.getProductViewInfo(prodId);

            GetIp getIp = new GetIp();
            String ip = getIp.getIp(request);

            VisitTotle visitTotle1 = new VisitTotle();
            VisitTotle visitTotle2 = new VisitTotle();

            Date date = new Date();
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String time = format1.format(date);

            User user = (User)request.getSession().getAttribute("user");

            if(user == null){
                visitTotle2 = visitTotleService.getVisitTotle(ip, prodId);
                visitTotle1.setUserId(null);
            }
            else{
                visitTotle2 = visitTotleService.getVisitTotle(ip, prodId,user.getId());
                visitTotle1.setUserId(user.getId());
            }

            if (visitTotle2 == null) {
                visitTotle1.setIp(ip);
                visitTotle1.setProdId(prodId);
                visitTotle1.setVisitTime(time);

                product.setVisitCount(product.getVisitCount() + 1);
                productService.updateProductCount(product);

                visitTotleService.saveVisitTotle(visitTotle1);
            }else{
                DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");

                String time1 = visitTotleService.getVisitTotle(ip, prodId).getVisitTime().replace("-","").replace(":","").replace(" ","");
                Long time2 = Long.parseLong(time1);
                Long time3 = Long.parseLong(format2.format(date));

                if(time3-time2 > 5){
                    product.setVisitCount(product.getVisitCount() + 1);
                    productService.updateProductCount(product);

                    visitTotle1.setIp(ip);
                    visitTotle1.setProdId(prodId);
                    visitTotle1.setVisitTime(time);

                    visitTotleService.saveVisitTotle(visitTotle1);
                }
            }

            ModelAndView view = new ModelAndView();
            view.addObject("product", product);
            view.addObject("navigationFather", navigationService.getNavigationList());
            view.addObject("company", companyService.getCompanyInfo());
            view.addObject("contextPath", contextPath);
            view.setViewName("customer/product_view");
            return view;
        }
    }


}
