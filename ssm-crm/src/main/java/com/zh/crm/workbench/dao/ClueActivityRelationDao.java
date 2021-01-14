package com.zh.crm.workbench.dao;

import com.zh.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationDao {

    //添加关联市场
    int save(ClueActivityRelation car);

    //删除关联市场
    int delete(String id);

    //查询关联市场
    List<ClueActivityRelation> getConActList(String clueId);

    //删除的条数
    int getCountByCids(String[] ids);

    //删除
    int deleteByCids(String[] ids);

}
