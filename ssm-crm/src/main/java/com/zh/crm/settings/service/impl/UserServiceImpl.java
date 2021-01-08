package com.zh.crm.settings.service.impl;

import com.zh.crm.exception.LoginException;
import com.zh.crm.settings.dao.UserDao;
import com.zh.crm.settings.domain.User;
import com.zh.crm.settings.service.UserService;
import com.zh.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UserServiceImpl
 * @Date 2020-12-29 10:22
 * @Created by 张浩
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd) throws LoginException {
        Map<String ,String> map = new HashMap<String, String>();
        map.put("loginAct" ,loginAct);
        map.put("loginPwd" ,loginPwd);
        User user = userDao.login(map);
        if (user == null){

            throw new LoginException("账号密码错误");
        }
        if (user.getExpireTime().compareTo(DateTimeUtil.getSysTime())<0  ){
            throw new LoginException("账号已失效");
        }
        if ("0".equals(user.getLockState()) ){
            throw new LoginException("账号已锁定");
        }

        return user;
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }
}
