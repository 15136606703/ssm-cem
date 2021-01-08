package com.zh.crm.handler;

import com.zh.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname MyInterceptor
 * @Date 2021-1-4 13:56
 * @Created by 张浩
 *
 * 拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    //验证用户登陆信息
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取登陆地址
        String url = request.getRequestURI();
        //判断当前请求地址是否登录地址
        if(url.contains("login")||url.contains("Login")){
            //请求登陆直接放行
            return true;
        }else{
            //从session获取user的值
            User user = (User) request.getSession().getAttribute("USER_SESSION");
            if(user!=null){
                //说明已经登陆在，放行
                return true;
            }else {
                //没有登陆，跳转到登陆界面
                response.sendRedirect(request.getContextPath()+"/user/toLoginPage");
            }

        }
        //默认拦截
        return false;
    }

}
