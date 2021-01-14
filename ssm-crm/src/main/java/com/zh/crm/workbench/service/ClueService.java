package com.zh.crm.workbench.service;

import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.domain.ActivityRemark;
import com.zh.crm.workbench.domain.Clue;
import com.zh.crm.workbench.domain.ClueRemark;

import java.util.List;
import java.util.Map;

/**
 * @Classname ClueService
 * @Date 2021-1-12 17:12
 * @Created by 张浩
 */
public interface ClueService {

    boolean save(Clue a);

    PaginationVo<Clue> pageList(Map<String, Object> map);

    boolean delete(String[] ids);

    boolean update(Clue a);

    Clue getById(String id);

    List<ClueRemark> getRemarkListByCid(String clueId);

    boolean deleteRemark(String id);

    boolean saveRemark(ClueRemark cr);

    boolean updateRemark(ClueRemark cr);
}
