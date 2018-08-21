package com.gyh.ssm.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gyh.ssm.security.domain.SysUser;

public class MyUserDetail implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1617833287620846674L;
	private SysUser sysUser;
	private Collection<? extends GrantedAuthority> authorities;

	public MyUserDetail(SysUser user,Collection<? extends GrantedAuthority> authorities) {
		this.sysUser = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		return sysUser.getPassword();
	}

	@Override
	public String getUsername() {
		return sysUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return sysUser.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return sysUser.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return sysUser.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return sysUser.isEnabled();
	}

}
