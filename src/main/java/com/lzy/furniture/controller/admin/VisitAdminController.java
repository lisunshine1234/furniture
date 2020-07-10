package com.lzy.furniture.controller.admin;

import com.lzy.furniture.entity.VisitTotle;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import com.lzy.furniture.service.VisitTotleService;
import com.lzy.furniture.util.IpWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VisitAdminController {
    @Autowired
    private VisitTotleService visitTotleService;
    @Autowired
    private IndexService indexService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/admin/visit")
    public ModelAndView visitAll() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<VisitTotle> visitTotleList = visitTotleService.getAll();

        String[] cityCount = new String[34];

        cityCount[0] = "天津";
        cityCount[1] = "北京";
        cityCount[2] = "上海";
        cityCount[3] = "重庆";
        cityCount[4] = "河北";
        cityCount[5] = "河南";
        cityCount[6] = "云南";
        cityCount[7] = "辽宁";
        cityCount[8] = "黑龙江";
        cityCount[9] = "湖南";
        cityCount[10] = "安徽";
        cityCount[11] = "山东";
        cityCount[12] = "新疆";
        cityCount[13] = "江苏";
        cityCount[14] = "浙江";
        cityCount[15] = "江西";
        cityCount[16] = "湖北";
        cityCount[17] = "广西";
        cityCount[18] = "甘肃";
        cityCount[19] = "山西";
        cityCount[20] = "内蒙古";
        cityCount[21] = "陕西";
        cityCount[22] = "吉林";
        cityCount[23] = "福建";
        cityCount[24] = "贵州";
        cityCount[25] = "广东";
        cityCount[26] = "青海";
        cityCount[27] = "西藏";
        cityCount[28] = "四川";
        cityCount[29] = "宁夏";
        cityCount[30] = "海南";
        cityCount[31] = "台湾";
        cityCount[32] = "香港";
        cityCount[33] = "澳门";

        Integer[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


        IpWork ipWork = new IpWork();

        for (VisitTotle visitTotle : visitTotleList) {

                String city = ipWork.getIpCity(visitTotle.getIp());
                if (city != null) {
                    for (int i = 0; i < 34; i++) {
                        if (city.contains(cityCount[i])) {
                            count[i] = count[i] + 1;
                            break;
                        }
                    }
                }


        }

        ModelAndView view = new ModelAndView();
        view.addObject("cityCount", count);
        view.addObject("messageNotReadCount", indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/visit");
        return view;
    }
}
