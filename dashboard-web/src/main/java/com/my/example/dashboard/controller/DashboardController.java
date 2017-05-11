package com.my.example.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DashboardController {

    @RequestMapping(value = {"", "/", "/dashboard", "/index"})
    public ModelAndView dashboard() {
        return new ModelAndView("/dashboard");
    }

    @RequestMapping(value = "/login-out")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        WebUtils.setSessionAttribute(request, "user", null);
//        Cookie cookie = WebUtils.getCookie(request, "utoken");
//        if (cookie != null) {
//            cookie.setMaxAge(0);
//            cookie.setValue(null);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//        }
//        response.sendRedirect("/");
    }
}
