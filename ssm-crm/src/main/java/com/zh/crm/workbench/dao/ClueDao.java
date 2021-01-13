package com.zh.crm.workbench.dao;
import com.zh.crm.workbench.domain.Clue;
import com.zh.crm.workbench.domain.ClueRemark;

import java.util.List;
import java.util.Map;

public interface ClueDao {

    int save(Clue a);

    int getTotalByCondition(Map<String, Object> map);

    List<Clue> getClueListByCondition(Map<String, Object> map);

    int delete(String[] ids);

    Clue getById(String id);

    int update(Clue a);


}
