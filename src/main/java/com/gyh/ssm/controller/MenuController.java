package com.gyh.ssm.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/menu")
public class MenuController {

	private final Logger LOG = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String  logout(HttpServletRequest request, HttpServletResponse response, SecurityContextLogoutHandler logoutHandler) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
			new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY).logout(request, response, auth);
		}
		Enumeration<String> em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		request.getSession().removeAttribute("JSESSIONID");
		request.getSession().invalidate();
		logoutHandler.logout(request, response, auth);
		return "common/login";
	}

	@RequestMapping(value="/system/index", method=RequestMethod.GET)
	public String showIndex() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if( auth != null && auth.getPrincipal() != null
				&& auth.getPrincipal() instanceof UserDetails) {
			LOG.warn("username="  + ((UserDetails) auth.getPrincipal()).getUsername());
		}
		return "index";
	}
	
	@RequestMapping(value="/system/ios", method=RequestMethod.GET)
	public String showIos() {
		return "ios";
	}
	
	@RequestMapping(value="/system/svn", method=RequestMethod.GET)
	public String showSvn() {
		return "svn";
	}
	
	@RequestMapping(value="/system/error", method=RequestMethod.GET)
	public String showError() {
		return "common/error";
	}
}
