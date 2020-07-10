package com.lzy.furniture.controller.admin;

import com.lzy.furniture.entity.User;
import com.lzy.furniture.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserAdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private IndexService indexService;

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RegisterService registerService;

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @RequestMapping("/admin/user")
    public ModelAndView user() {
        ModelAndView view = new ModelAndView();
        view.addObject("userList", userService.getUserAllList());
        view.addObject("messageNotReadCount",indexService.getMessageNotReadCount());
        view.addObject("company", companyService.getCompanyInfo());
        view.addObject("contextPath", contextPath);
        view.setViewName("admin/user");
        return view;

    }

    @PostMapping("/admin/updateUser")
    public String addUser(@RequestParam("userId") Integer userId,
                          @RequestParam("userName") String loginName,
                          @RequestParam("callName") String userName,
                          @RequestParam("passWord") String password,
                          @RequestParam("tel") String phone,
                          @RequestParam("email") String email,
                          @RequestParam("question") String question,
                          @RequestParam("answer") String answer,
                          @RequestParam("registerTime") String registerTime) {


        User user = new User();
        user.setId(userId);
        user.setLoginName(loginName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setQuestion(question);
        user.setAnswer(answer);
        user.setRegisterTime(registerTime);

        registerService.registerUser(user);

        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam("userId") Integer userId){
        userService.deleteUser(userId);
        return "1";
    }

    @RequestMapping("/admin/findUser")
    @ResponseBody
    public User findUser(@RequestParam("userId") Integer userId){
        System.out.println(userId);
        return userService.findUser(userId);
    }
}
