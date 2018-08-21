package com.gyh.ssm.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gyh.ssm.security.MyUserDetail;
import com.gyh.ssm.security.dao.SysRoleDAO;
import com.gyh.ssm.security.dao.SysUserDAO;
import com.gyh.ssm.security.domain.SysRole;
import com.gyh.ssm.security.domain.SysUser;

@Service
public class MyUserDetailService implements UserDetailsService  {
	@Autowired
	private SysUserDAO userDAO;
	@Autowired
	private SysRoleDAO roleDAO;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SysUser user = userDAO.findRecordByName(username);
		if(user==null)
		{
			throw new  UsernameNotFoundException("找不到该用户");
		}
		List<SysRole> roleList = roleDAO.findAllRecord(user.getId(), null);
		return new MyUserDetail(user, getAuthorities(roleList));
	}

	private Collection<GrantedAuthority> getAuthorities(List<SysRole> roleList) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(SysRole role : roleList) {
			SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
			grantedAuthorities.add(grantedAuthority);
		}
		return grantedAuthorities;
	}

}
