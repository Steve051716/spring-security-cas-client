package com.gyh.ssm.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gyh.ssm.security.domain.SysRole;

public interface SysRoleDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> findAllRecord(@Param(value="userId")Long userId, @Param(value="username")String username);
}