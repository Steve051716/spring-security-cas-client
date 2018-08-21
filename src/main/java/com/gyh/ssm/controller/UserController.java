package com.gyh.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyh.ssm.security.SecurityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import com.gyh.ssm.security.SecurityProvider;
import com.gyh.ssm.security.domain.SysUser;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private SecurityProvider securityProvider;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(SysUser user, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		try {
            Authentication authentication = securityProvider.authenticate(authRequest); //调用loadUserByUsername
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
            return "redirect:/menu/system/index";
        } catch (AuthenticationException ex) {
        	return "redirect:/menu/login";
        }
	}
}
