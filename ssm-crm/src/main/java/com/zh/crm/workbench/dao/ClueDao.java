package com.zh.crm.workbench.dao;
import java.util.Map;

public interface ClueDao {

    int save(ClueDao a);

    int getTotalByCondition(Map<String, Object> map);

    int delete(String[] ids);

    ClueDao getById(String id);

    int update(ClueDao a);

    ClueDao detail(String id);

}
