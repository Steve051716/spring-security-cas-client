package com.gyh.ssm.security.dao;

import com.gyh.ssm.security.domain.SysRoleUser;

public interface SysRoleUserDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}