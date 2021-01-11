package com.zh.settings.test;

import com.zh.crm.exception.LoginException;
import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.utils.MD5Util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Classname MyTest2
 * @Date 2020-12-24 17:13
 * @Created by 张浩
 */
public class MyTest2 {

    @Test
    public void test01(){
        //定义两种不同格式的字符串
        String objectStr="{\"name\":\"JSON\",\"loginAct\":\"24\",\"email\":\"北京市西城区\"}";
        String arrayStr="[{\"name\":\"JSON\",\"loginAct\":\"24\",\"email\":\"北京市西城区\"}]";

        //1、使用JSONObject
        JSONObject jsonObject=JSONObject.fromObject(objectStr);
        User user=(User)JSONObject.toBean(jsonObject, User.class);

       // User user1=(User)JSONObject.toBean(JSONObject.fromObject(objectStr), User.class);

        //2、使用JSONArray
        JSONArray jsonArray=JSONArray.fromObject(arrayStr);
        //获得jsonArray的第一个元素
        Object o=jsonArray.get(0);
        JSONObject jsonObject2=JSONObject.fromObject(o);
        User stu2=(User)JSONObject.toBean(jsonObject2, User.class);
        System.out.println("user:"+user);
        System.out.println("stu2:"+stu2);

    }
}

