package com.gyh.ssm.security.dao;

import com.gyh.ssm.security.domain.SysResource;

public interface SysResourceDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
}