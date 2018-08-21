package com.gyh.ssm.security.dao;

import org.springframework.stereotype.Repository;

import com.gyh.ssm.security.domain.SysUser;


@Repository
public interface SysUserDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    public SysUser findRecord(SysUser user);
    
    public SysUser findRecordByName(String username);
}