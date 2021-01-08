package com.zh.crm.settings.controller;

import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.utils.MD5Util;
import com.zh.crm.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname UserController
 * @Date 2020-12-29 10:24
 * @Created by 张浩
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService service;

    @RequestMapping("/toLoginPage")
    public String toLoginPage(){
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(String loginAct, String loginPwd, HttpServletRequest request) {
        JsonResult jsonResult;
        loginPwd = MD5Util.getMD5(loginPwd);
        try {
            User user = service.login(loginAct,loginPwd);
            request.getSession().setAttribute("USER_SESSION",user);
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"success","");

        }catch (Exception e){
            String msg = e.getMessage();
            jsonResult = new JsonResult(JsonResult.STATE_ERROR,msg,"");
        }
        return jsonResult;
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().setAttribute("USER_SESSION",null);
        return "redirect:/login.jsp";
    }

    @RequestMapping("getUserList")
    @ResponseBody
    public JsonResult getUserList(){
        List<User> users =service.getUserList();
        JsonResult jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"success",users);
        return jsonResult;
    }
}
