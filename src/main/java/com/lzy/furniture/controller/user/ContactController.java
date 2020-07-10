package com.lzy.furniture.controller.user;

import com.lzy.furniture.entity.Message;
import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.CompanyService;
import com.lzy.furniture.service.ContactService;
import com.lzy.furniture.service.NavigationService;
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
import java.util.Date;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @RequestMapping("/contact")
    public ModelAndView contactCompany() {
        ModelAndView view = new ModelAndView();
        view.addObject("navigationFather", navigationService.getNavigationList());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("customer/contact");
        return view;
    }

    @RequestMapping("/message")
    @ResponseBody
    public Integer message(@RequestParam(value = "messageInfo", required = false) String messageInfo,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return 2;
        } else {
            if (messageInfo == null) {
                try {
                    response.sendRedirect(contextPath+"/contact");
                } catch (Exception ignored) {
                }
            } else {
                if (messageInfo.length() <= 200) {
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = format.format(date);

                    Message message = new Message();

                    GetIp getIp = new GetIp();

                    message.setMessInfo(messageInfo);
                    message.setMessTime(time);
                    message.setState(0);
                    message.setUserId(user.getId());
                    message.setIp(getIp.getIp(request));

                    if (contactService.messageSend(message)) {
                        return 1;
                    }
                }
            }
            return 0;
        }

    }
}
