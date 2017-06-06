package com.my.example.dashboard.controller;

import com.my.example.dashboard.common.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DashboardController {

    @RequestMapping(value = {"", "/", "/dashboard", "/index"})
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/search");
        modelAndView.addObject("pager", new Pager());
        return modelAndView;
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
