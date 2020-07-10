package com.lzy.furniture.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lzy.furniture.entity.News;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.NewsService;
import com.lzy.furniture.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.text.Format;
import java.util.*;

@Controller
public class NewsAdminController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private IndexService indexService;

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @Value("${LocalSavaUrl}")
    private String LocalSavaUrl;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping("/admin/news")
    public ModelAndView news() {
        ModelAndView view = new ModelAndView();
        view.addObject("newsList", newsService.getNewsAll());
        view.addObject("messageNotReadCount", indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/news");
        return view;
    }

    @RequestMapping("/admin/updateNews")
    @ResponseBody
    public String updateMessage(@RequestParam("newsId") Integer newsId,
                                @RequestParam("state") Integer state,
                                @RequestParam("address") String address,
                                @RequestParam("name") String name) {
        News news = new News();
        news.setId(newsId);
        news.setState(state);
        news.setAddress(address);
        news.setName(name);

        newsService.updateNewsState(news);
        return "1";
    }

    @PostMapping("/admin/uploadImg")
    @ResponseBody
    public String uploadImg(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(value = "uploadImg", required = false) MultipartFile file) {

        FileUpload fileUpload = new FileUpload();
        String fileName = fileUpload.uploadFile(file, LocalSavaUrl, "pictures/news/");

        Map<String, String> map = new HashMap<>();
        map.put("code", "1");
        map.put("msg", fileName);
        return JSON.toJSONString(map);
    }

    @PostMapping("/admin/saveNews")
    @ResponseBody
    public String saveNews(@RequestParam(value = "newsId", required = false) Integer newsId,
                           @RequestParam(value = "newsName") String newsName,
                           @RequestParam(value = "checkbox") Integer checkbox,
                           @RequestParam(value = "newsFileName", required = false) String newsFileName,
                           HttpServletRequest request) {
        System.out.println(newsFileName);
        News news = new News();
        if (newsId != null) {
            news.setId(newsId);
        }

        if (newsFileName != null) {
            news.setAddress(newsFileName);
        } else {
            news.setAddress("/images/0.jpg");

        }

        news.setName(newsName);
        news.setState(checkbox);

        request.getSession().setAttribute("newsFileName", null);
        return newsService.saveNews(news) ? "1" : "0";
    }

    @GetMapping("/admin/deleteNews")
    @ResponseBody
    public String saveNews(@RequestParam(value = "newsId", required = false) Integer newsId) {
        System.out.println(newsId);
        return newsService.deleteNews(newsId) ? "1" : "0";
    }

}
