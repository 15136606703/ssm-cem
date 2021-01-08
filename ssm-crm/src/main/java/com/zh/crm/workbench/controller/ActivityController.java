package com.zh.crm.workbench.controller;

import com.zh.crm.settings.domain.User;
import com.zh.crm.utils.DateTimeUtil;
import com.zh.crm.utils.UUIDUtil;
import com.zh.crm.vo.JsonResult;
import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.domain.ActivityRemark;
import com.zh.crm.workbench.service.ActivityService;
import com.zh.crm.workbench.service.impl.ActivityServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService as;
    @RequestMapping("/getActivePageList")
    @ResponseBody
    private JsonResult getActivePageList(Integer pageNum,Integer pageSize,String owner,String name,String startDate,String endDate){
        JsonResult jsonResult;
        if (pageNum==null||pageNum==0){
            pageNum = 1;
        }
        if (pageSize==null||pageSize==0){
            pageSize = 10;
        }

        int skipCount = (pageNum-1)*pageSize;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        map.put("owner",owner);
        map.put("name",name);
        map.put("startDate",startDate);
        map.put("endDate",endDate);

        PaginationVo<Activity> vo =  as.pageList(map);
        jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"success",vo);
        return jsonResult;
    }
    /*
    * 添加市场活动
    * */
    @RequestMapping("/save")
    @ResponseBody
    private JsonResult save(Activity activity,HttpServletRequest request) {
        JsonResult jsonResult;
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        //创建人
        String createBy = ((User)request.getSession().getAttribute("USER_SESSION")).getName();

        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        boolean flag = as.save(activity);
        if (flag){
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"success","");
        }else {
            jsonResult = new JsonResult(JsonResult.STATE_ERROR,"保存失败","");
        }
        return jsonResult;
    }


    /*private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        String noteContent = request.getParameter("noteContent");
        String id = request.getParameter("id");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "1";

        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setCreateBy(editTime);
        ar.setCreateTime(editBy);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag =  as.updateRemark(ar);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);

    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
        String noteContent = request.getParameter("noteContent");
        String activityId = request.getParameter("activityId");
        String id = UUIDUtil.getUUID();

        String createTime = DateTimeUtil.getSysTime();
        //创建人
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "0";
        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setActivityId(activityId);
        ar.setNoteContent(noteContent);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag =  as.saveRemark(ar);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);
        PrintJson.printJsonObj(response,map);
    }

    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.deleteRemark(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getRemarkListByAid(HttpServletRequest request, HttpServletResponse response) {
        String activityId = request.getParameter("activityId");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<ActivityRemark> rList =  as.getRemarkListByAid(activityId);
        PrintJson.printJsonObj(response,rList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a =  as.detail(id);
        request.setAttribute("a",a);
        //转发
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");

        String editTime = DateTimeUtil.getSysTime();
        //创建人
        String editBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editBy);


        boolean flag = as.update(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        String id = request.getParameter("id");
        Map<String,Object> map =  as.getUserListAndActivity(id);
        PrintJson.printJsonObj(response,map);
    }

    *//**
     * 删除
     *@Description 张浩
     *@param
     * @param request
     * @param response
     *@return void
     *@date 2020-12-7 15:12
     *@auther Administrator
     *//*
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String ids[] = request.getParameterValues("id");
        boolean flag =  as.delete(ids);
        PrintJson.printJsonFlag(response,flag);
    }


    *//*
    * 添加市场活动
    * *//*
    private void save(HttpServletRequest request, HttpServletResponse response) {
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        //创建人
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);


        boolean flag = as.save(a);

        PrintJson.printJsonFlag(response,flag);
    }*/



}
