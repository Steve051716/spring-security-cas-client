package com.gyh.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    private final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request, HttpServletResponse response, SecurityContextLogoutHandler logoutHandler) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logoutHandler.logout(request, response, auth );
        new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY).logout(request, response, auth);
        return "common/logout";
    }


    @GetMapping("/login")
    public String login() {
        return "redirect:/front/index";
    }

}