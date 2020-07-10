package com.lzy.furniture.controller.admin;

import com.lzy.furniture.entity.Message;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.ContactService;
import com.lzy.furniture.service.IndexService;
import com.lzy.furniture.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageAdminController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private IndexService indexService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/admin/message")
    public ModelAndView message(){
        ModelAndView view = new ModelAndView();
        view.addObject("contactList",contactService.getMessageList());
        view.addObject("messageNotReadCount",indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/message");
        return view;
    }

    @RequestMapping("/admin/updateMessage")
    @ResponseBody
    public String updateMessage(@RequestParam("messId") Integer messId,
                                @RequestParam("messInfo") String messInfo,
                                @RequestParam("messTime") String messTime,
                                @RequestParam("userId") Integer userId,
                                @RequestParam("ip") String ip){
        Message message = new Message();
        message.setId(messId);
        message.setMessInfo(messInfo);
        message.setMessTime(messTime);
        message.setUserId(userId);
        message.setIp(ip);
        message.setState(1);

        contactService.updateMessageState(message);
        return "1";
    }

    @RequestMapping("/admin/deleteMessage")
    @ResponseBody
    public String deleteMessage(@RequestParam("messId") Integer messId){

        contactService.deleteMessage(messId);
        return "1";
    }
}
