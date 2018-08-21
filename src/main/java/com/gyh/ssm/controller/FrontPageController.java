package com.gyh.ssm.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

@Controller
@RequestMapping(value = "/front")
public class FrontPageController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if( auth != null && auth.getPrincipal() != null
                && auth.getPrincipal() instanceof UserDetails) {
            modelMap.put("username", ((UserDetails) auth.getPrincipal()).getUsername());
            UserDetails userDetails = ((UserDetails) auth.getPrincipal());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
            // 将权限返回到前台
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
        }
        return "index";
    }
}
