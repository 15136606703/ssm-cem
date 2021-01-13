package com.zh.crm.workbench.dao;

import com.zh.crm.workbench.domain.ContactsActivityRelation;

import java.util.List;

public interface ContactsActivityRelationDao {

    //添加关联市场
    int save(ContactsActivityRelation car);

    //删除关联市场
    int delete(String id);

    //查询关联市场
    List<ContactsActivityRelation> getConActList(String clueId);

    //删除
    int getCountByCids(String[] ids);
}
