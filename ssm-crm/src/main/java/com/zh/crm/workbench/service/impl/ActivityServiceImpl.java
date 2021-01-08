package com.zh.crm.workbench.service.impl;

import com.zh.crm.settings.dao.UserDao;
import com.zh.crm.settings.domain.User;
import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.dao.ActivityDao;
import com.zh.crm.workbench.dao.ActivityRemarkDao;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.domain.ActivityRemark;
import com.zh.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private  ActivityDao activityDao;
    @Resource
    private ActivityRemarkDao remarkDao;
    @Resource
    private UserDao userDao;
    public boolean save(Activity a) {
        boolean flag = true;
        int count = activityDao.save(a);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    public PaginationVo<Activity> pageList(Map<String, Object> map) {

        //取得total
        int total = activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> aList = activityDao.getActivityListByCondition(map);

        PaginationVo<Activity> vo = new PaginationVo();
        vo.setTotal(total);
        vo.setDataList(aList);
        return vo;
    }

    public boolean delete(String[] ids) {
        boolean flag = true;
        //查询出需要删除备注的数量
        int count1 = remarkDao.getCountByAids(ids);
        //删除备注，返回受到影响的条数（实际删除的数量）
        int count2 = remarkDao.deleteByAids(ids);
        //删除市场活动
        if (count1!= count2) {
            flag = false;
        }

        int count3 = activityDao.delete(ids);
        if (count3!=ids.length){
            flag = false;
        }
        return flag;
    }

    public Map<String, Object> getUserListAndActivity(String id) {
        Activity a  = activityDao.getById(id);
        List<User> uList = userDao.getUserList();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("a" ,a);
        map.put("uList",uList);
        return map ;
    }

    public boolean update(Activity a) {
        boolean flag = true;
        int count = activityDao.update(a);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    public Activity detail(String id) {

        Activity a =  activityDao.detail(id);
        return a;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {

        List<ActivityRemark> rList =  remarkDao.getRemarkListByAid(activityId);
        return rList;
    }

    public boolean deleteRemark(String id) {
        boolean flag = true;

        int count = remarkDao.deleteById(id);
        if(count!=1){
            flag = false;
        }
        return flag;
    }

    public boolean saveRemark(ActivityRemark ar) {
        boolean flag = true;
        int count = remarkDao.saveRemark(ar);
        if (count!=1){

            flag = false;
        }
        return flag;
    }

    public boolean updateRemark(ActivityRemark ar) {

        boolean flag = true;
        int count = remarkDao.updateRemark(ar);
        if (count!=1){

            flag = false;
        }
        return flag;
    }

}
