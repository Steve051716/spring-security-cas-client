package com.gyh.ssm.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

/**
 * 根据用户的角色与资源的角色进行匹配。
 * @author 15801
 *
 */
public class MyAccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {

	private final Logger LOG = LoggerFactory.getLogger(MyAccessDecisionManager.class);
	
	@Override
	public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            		throws AccessDeniedException, InsufficientAuthenticationException {
		//这段代码其实不需要,因为spring-security-core-4.1.4.RELEASE-sources.jar!/org/springframework/security/access/intercept/AbstractSecurityInterceptor.java第215行判断提前返回了,不会进入decide方法
		if (CollectionUtils.isEmpty(configAttributes)) {
			throw new AccessDeniedException("not allow");
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((org.springframework.security.access.SecurityConfig) ca).getAttribute();
			LOG.warn("needRole=" + needRole);
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if(ga.getAuthority().equals(needRole)){
					//匹配到有对应角色,则允许通过
					return;
				}
			}
		}
		//该url有配置权限,但是当然登录用户没有匹配到对应权限,则禁止访问
		throw new AccessDeniedException("not allow");
	}
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}