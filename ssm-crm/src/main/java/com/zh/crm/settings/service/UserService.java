package com.zh.crm.settings.service;

import com.zh.crm.exception.LoginException;
import com.zh.crm.settings.domain.User;

import java.util.List;

/**
 * @Classname UserService
 * @Date 2020-12-29 10:19
 * @Created by 张浩
 */

public interface UserService {

    User login(String loginAct, String loginPwd) throws LoginException;

    List<User> getUserList();

}
