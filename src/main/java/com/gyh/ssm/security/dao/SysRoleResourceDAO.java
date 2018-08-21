package com.gyh.ssm.security.dao;

import java.util.List;
import java.util.Map;
import com.gyh.ssm.security.domain.SysRoleResource;

public interface SysRoleResourceDAO {
    int deleteByPrimaryKey(SysRoleResource key);

    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);
    
    public List<SysRoleResource> findRoleResource(Map<String, Object> params);
}