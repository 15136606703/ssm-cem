package com.zh.crm.settings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname ToPageHandler
 * @Date 2021-1-7 10:32
 * @Created by 张浩
 */
@Controller
public class ToPageHandler {
    @RequestMapping("/toPage")
    public String toPage(String url){
        //return "forward:/WEB-INF/jsp"+ url+".jsp";
        return url;
    }
}
