package com.zh.crm.workbench.controller;

import com.zh.crm.settings.domain.User;
import com.zh.crm.utils.DateTimeUtil;
import com.zh.crm.utils.UUIDUtil;
import com.zh.crm.vo.JsonResult;
import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.domain.ActivityRemark;
import com.zh.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    * 添加/修改市场活动
    * */
    @RequestMapping("/save")
    @ResponseBody
    private JsonResult save(Activity activity,HttpServletRequest request) {
        String msg = "";
        String state = "";
        String id = activity.getId();
        boolean flag = false;
        //获取session中的登陆用户
        String createBy = ((User)request.getSession().getAttribute("USER_SESSION")).getName();
        if (id!=null &&id!=""){ //修改

            String editTime = DateTimeUtil.getSysTime();
            activity.setEditTime(editTime);
            activity.setEditBy(createBy);
            flag = as.update(activity);
            if (flag){
                state = JsonResult.STATE_SUCCESS;
                msg = "修改成功";
            }else {
                state = JsonResult.STATE_ERROR;
                msg = "修改失败";
            }

        }else { // 添加
            id = UUIDUtil.getUUID();
            String createTime = DateTimeUtil.getSysTime();


            activity.setId(id);
            activity.setCreateTime(createTime);
            activity.setCreateBy(createBy);
            flag = as.save(activity);
            if (flag){
                state = JsonResult.STATE_SUCCESS;
                msg = "添加成功";
            }else {
                state = JsonResult.STATE_ERROR;
                msg = "添加失败";
            }
        }

        return new JsonResult(state,msg,"");
    }

    /*
    * 活动详情，自动重定向到详情页
    * */
    @RequestMapping("/getByIdActivity")
    private ModelAndView getByIdActivity(String id){
        Activity a =  as.detail(id);
        return new ModelAndView("/workbench/activity/detail","a",a);
    }

    /*
     * 活动详情
     * */
    @RequestMapping("/getByIdAct")
    @ResponseBody
    private JsonResult getByIdAct(String id){
        Map<String,Object> map =  as.getUserListAndActivity(id);
        return  new JsonResult(JsonResult.STATE_SUCCESS,"success",map);
    }

    /*
    * 删除活动，批量删除
    * */
    @RequestMapping("/deleteAct")
    @ResponseBody
    private JsonResult deleteAct(String[] ids){
        JsonResult jsonResult;
        boolean flag =  as.delete(ids);
        if (flag){
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"删除成功！","");
        }else{
            jsonResult  = new JsonResult(JsonResult.STATE_ERROR,"删除失败！","");
        }
        return jsonResult;
    }
    /*
    * getRemarkListByAid
    * 获取备注列表
    * */
    @RequestMapping("/getRemarkListByAid")
    @ResponseBody
    private JsonResult getRemarkListByAid(String activityId){
        JsonResult jsonResult;
        List<ActivityRemark> rList =  as.getRemarkListByAid(activityId);
        if (rList!=null && !rList.isEmpty()){
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"success",rList);
        }else {
            jsonResult  = new JsonResult(JsonResult.STATE_ERROR,"没有查询到数据",rList);
        }
        return  jsonResult;
    }

    /*
    * 添加保存，修改活动备注
    * */
    @RequestMapping("/saveRemark")
    @ResponseBody
    private JsonResult saveRemark(ActivityRemark ar,HttpServletRequest request){
        String msg = "";
        String state = "";
        String id = ar.getId();
        boolean flag = false;
        //获取session中的登陆用户
        String user = ((User)request.getSession().getAttribute("USER_SESSION")).getName();
        String time = DateTimeUtil.getSysTime();
        if (id!=null &&id!=""){ //修改
            ar.setEditBy(user);
            ar.setEditTime(time);
            ar.setEditFlag("1");
            flag =  as.updateRemark(ar);

            if (flag){
                state = JsonResult.STATE_SUCCESS;
                msg = "修改成功";
            }else {
                state = JsonResult.STATE_ERROR;
                msg = "修改失败";
            }

        }else { // 添加
            id = UUIDUtil.getUUID();
            String editFlag = "0";
            ar.setId(id);
            ar.setCreateBy(user);
            ar.setCreateTime(time);
            ar.setEditFlag(editFlag);
            flag =  as.saveRemark(ar);
            if (flag){
                state = JsonResult.STATE_SUCCESS;
                msg = "添加成功";
            }else {
                state = JsonResult.STATE_ERROR;
                msg = "添加失败";
            }
        }

        return new JsonResult(state,msg,ar);
    }

    /*
     * 删除活动备注
     * */
    @RequestMapping("/deleteRemark")
    @ResponseBody
    private JsonResult deleteRemark(String id){
        JsonResult jsonResult;
        boolean flag = as.deleteRemark(id);
        if (flag){
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"删除成功！","");
        }else{
            jsonResult  = new JsonResult(JsonResult.STATE_ERROR,"删除失败！","");
        }
        return jsonResult;
    }



}
