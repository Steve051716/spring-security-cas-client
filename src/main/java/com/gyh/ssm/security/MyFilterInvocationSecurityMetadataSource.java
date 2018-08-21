package com.gyh.ssm.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import com.gyh.ssm.security.dao.SysRoleDAO;
import com.gyh.ssm.security.dao.SysRoleResourceDAO;
import com.gyh.ssm.security.domain.SysRoleResource;

public class MyFilterInvocationSecurityMetadataSource implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final Logger LOG = LoggerFactory.getLogger(MyAccessDecisionManager.class);
    
    @Autowired
    private SysRoleDAO sysRoleDAO;
    @Autowired
    private SysRoleResourceDAO sysRoleResourceDAO;
    
    private List<SysRoleResource> urlRoleList;
    
	@Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
		if(urlRoleList == null) {
			urlRoleList = sysRoleResourceDAO.findRoleResource(null);
		}
        List<String> attributeList = new ArrayList<String>();
        for(SysRoleResource sysRoleResource : urlRoleList) {
        	LOG.warn("rolename=" + sysRoleResource.getRoleName() + "; url=" + sysRoleResource.getResourceUrl());
        	if(antPathMatcher.match(sysRoleResource.getResourceUrl(),url)){
        		// 若请求的地址能够匹配到数据库已配置的资源地址，则获取该资源地址对应的角色。
        		attributeList.add(sysRoleResource.getRoleName());
            }
        	
        }
        if(attributeList != null && attributeList.size() > 0) {
        	String[] attributeArray = attributeList.toArray(new String[attributeList.size()]);
        	return SecurityConfig.createList(attributeArray);
        }
        //没有匹配到,默认是要登录才能访问 isAnonymous
        return SecurityConfig.createList("ROLE_ANONYMOUS");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
