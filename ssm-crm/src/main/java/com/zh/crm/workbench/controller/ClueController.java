package com.zh.crm.workbench.controller;

import com.zh.crm.settings.domain.User;
import com.zh.crm.utils.DateTimeUtil;
import com.zh.crm.utils.UUIDUtil;
import com.zh.crm.vo.JsonResult;
import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.ActivityRemark;
import com.zh.crm.workbench.domain.Clue;
import com.zh.crm.workbench.domain.ClueRemark;
import com.zh.crm.workbench.service.ClueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ClueController
 * @Date 2021-1-12 17:11
 * @Created by 张浩
 */
@Controller
@RequestMapping("/clue")
public class ClueController {

    @Resource
    private ClueService clueService;

    @RequestMapping("/getCluePageList")
    @ResponseBody
    private JsonResult getCluePageList(Integer pageNum,Integer pageSize,String owner,String name,String startDate,String endDate){
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

        PaginationVo<Clue> vo =  clueService.pageList(map);
        jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"success",vo);
        return jsonResult;
    }
    /*
     * 添加/修改市场活动
     * */
    @RequestMapping("/save")
    @ResponseBody
    private JsonResult save(Clue Clue, HttpServletRequest request) {
        String msg = "";
        String state = "";
        String id = Clue.getId();
        boolean flag = false;
        //获取session中的登陆用户
        String createBy = ((User)request.getSession().getAttribute("USER_SESSION")).getName();
        if (id!=null &&id!=""){ //修改

            String editTime = DateTimeUtil.getSysTime();
            Clue.setEditTime(editTime);
            Clue.setEditBy(createBy);
            flag = clueService.update(Clue);
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


            Clue.setId(id);
            Clue.setCreateTime(createTime);
            Clue.setCreateBy(createBy);
            flag = clueService.save(Clue);
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
     * 活动详情
     * */
    @RequestMapping("/getByIdClue")
    @ResponseBody
    private JsonResult getByIdClue(String id){
        Clue clue =  clueService.getById(id);
        return new JsonResult(JsonResult.STATE_SUCCESS,"success",clue);
    }

    /*
     * 删除活动，批量删除
     * */
    @RequestMapping("/deleteClue")
    @ResponseBody
    private JsonResult deleteClue(String[] ids){
        JsonResult jsonResult;
        boolean flag =  clueService.delete(ids);
        if (flag){
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"删除成功！","");
        }else{
            jsonResult  = new JsonResult(JsonResult.STATE_ERROR,"删除失败！","");
        }
        return jsonResult;
    }

    /*
     * getRemarkListByCid
     * 获取线索备注列表
     * */
    @RequestMapping("/getRemarkListByCid")
    @ResponseBody
    private JsonResult getRemarkListByCid(String clueId){
        JsonResult jsonResult;
        List<ClueRemark> rList =  clueService.getRemarkListByCid(clueId);
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
    private JsonResult saveRemark(ClueRemark ar,HttpServletRequest request){
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
            flag =  clueService.updateRemark(ar);

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
            flag =  clueService.saveRemark(ar);
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
        boolean flag = clueService.deleteRemark(id);
        if (flag){
            jsonResult = new JsonResult(JsonResult.STATE_SUCCESS,"删除成功！","");
        }else{
            jsonResult  = new JsonResult(JsonResult.STATE_ERROR,"删除失败！","");
        }
        return jsonResult;
    }



}
