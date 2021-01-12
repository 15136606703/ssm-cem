package com.zh.crm.workbench.service;

import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.domain.Activity;
import com.zh.crm.workbench.domain.Clue;

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

    Clue detail(String id);
}
