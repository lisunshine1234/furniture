package com.lzy.furniture.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lzy.furniture.entity.NavigationChild;
import com.lzy.furniture.entity.Product;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.ProductService;
import com.lzy.furniture.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductAdminController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NavigationService navigationService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Value("${LocalSavaUrl}")
    private String LocalSavaUrl;

    @RequestMapping("/admin/product")
    public ModelAndView product() {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", productService.getAllProductOrderTime());
        view.addObject("navigationChildList", navigationService.getNavigationChildList());
        view.addObject("navigationFatherList", navigationService.getNavigationList());
        view.addObject("messageNotReadCount", indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/product");
        return view;
    }

    @RequestMapping("/admin/productNaviChild")
    @ResponseBody
    public List<NavigationChild> productNaviChild(@RequestParam(value = "fatherId") Integer fatherId) {
        return navigationService.getNavigationChildList(fatherId);
    }


    @PostMapping("/admin/uploadProductImg")
    @ResponseBody
    public String uploadProductImg(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(value = "uploadImg", required = false) MultipartFile file) {
        FileUpload fileUpload = new FileUpload();

        String fileName = fileUpload.uploadFile(file, LocalSavaUrl, "pictures/product/");

        Map<String, String> map = new HashMap<>();
        map.put("code", "1");
        map.put("msg", fileName);
        return JSON.toJSONString(map);
    }

    @PostMapping("/admin/addProduct")
    @ResponseBody
    public String addProduct(@RequestParam(value = "prodId", required = false) Integer prodId,
                             @RequestParam(value = "prodName") String prodName,
                             @RequestParam(value = "material") String material,
                             @RequestParam(value = "length") Integer length,
                             @RequestParam(value = "width") Integer width,
                             @RequestParam(value = "height") Integer height,
                             @RequestParam(value = "fatherSelect") Integer fatherSelect,
                             @RequestParam(value = "childSelect") Integer childSelect,
                             @RequestParam(value = "describe") String describe,
                             @RequestParam(value = "fileUploadList") String fileUploadListIn,
                             HttpServletRequest request) {

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        Product product = new Product();

        if (prodId != null) {
            if (productService.getProductViewInfo(prodId) != null) {
                product = productService.getProductViewInfo(prodId);
            }
        } else {
            product.setAddress1("/images/0.jpg");
            product.setVisitCount(0);
        }
        List<String> fileUploadList = JSON.parseArray(fileUploadListIn, String.class);
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress1(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress1(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress1(null);
                    }
                    break;
                case 1:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress2(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress2(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress2(null);
                    }
                    break;
                case 2:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress3(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress3(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress3(null);
                    }
                    break;
                case 3:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress4(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress4(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress4(null);
                    }
                    break;
                case 4:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress5(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress5(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress5(null);
                    }
                    break;
                case 5:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress6(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress6(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress6(null);
                    }
                    break;
                case 6:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress7(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress7(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress7(null);
                    }
                    break;
                case 7:
                    if (i < fileUploadList.size()) {
                        if (fileUploadList.get(i).substring(0, contextPath.length()).equals(contextPath)) {
                            product.setAddress8(fileUploadList.get(i).substring(contextPath.length()));
                        } else {
                            product.setAddress8(fileUploadList.get(i));
                        }
                    } else {
                        product.setAddress8(null);
                    }
                    break;
            }
        }


        product.setProdName(prodName);
        product.setMaterial(material);
        product.setLength(length);
        product.setWidth(width);
        product.setHeight(height);
        product.setFatherId(fatherSelect);
        product.setChildId(childSelect);
        product.setProdDescribe(describe);
        product.setUploadTime(time);

        return productService.saveProduct(product) ? "1" : "0";
    }

    @RequestMapping("/admin/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam(value = "prodId", required = false) Integer prodId) {
        return productService.deleteProduct(prodId) ? "1" : "0";
    }

}
