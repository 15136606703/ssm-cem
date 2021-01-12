package com.zh.crm.workbench.service.impl;

import com.zh.crm.vo.PaginationVo;
import com.zh.crm.workbench.dao.ClueDao;
import com.zh.crm.workbench.domain.Clue;
import com.zh.crm.workbench.service.ClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Classname ClueServiceImpl
 * @Date 2021-1-12 17:12
 * @Created by 张浩
 */
@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private ClueDao clueDao;

    @Override
    public boolean save(Clue a) {

        return false;
    }

    @Override
    public PaginationVo<Clue> pageList(Map<String, Object> map) {
        return null;
    }

    @Override
    public boolean delete(String[] ids) {
        return false;
    }

    @Override
    public boolean update(Clue a) {
        return false;
    }

    @Override
    public Clue detail(String id) {
        return null;
    }
}
