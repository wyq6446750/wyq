package com.my.example.dashboard.controller;

import com.my.example.dashboard.common.Pager;
import com.my.example.dashboard.model.config.Configs;
import com.my.example.dashboard.model.domain.LoginTicket;
import com.my.example.dashboard.model.domain.User;
import com.my.example.dashboard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Date:17/2/4
 * Time:下午3:35
 *
 * @author yongquan.wen
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/search")
    public ModelAndView index(@RequestParam(name = "ajax", defaultValue = "false", required = false) boolean ajax) {
        ModelAndView modelAndView = new ModelAndView(ajax ? "user/list" : "user/search");
        if (ajax) {
            List<User> list = userService.selectAll();
            Pager<User> pager = new Pager<>(1, 20, list.size(), list);
            modelAndView.addObject("pager", pager);
        } else {
            Pager pager = new Pager();
            modelAndView.addObject("pager", pager);
        }
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        Object object = WebUtils.getSessionAttribute(request, Configs.SESSION_USER_ATTR);
        Cookie cookie = WebUtils.getCookie(request, Configs.COOKIE_NAME);
        String ticket = null == cookie ? null : WebUtils.getCookie(request, Configs.COOKIE_NAME).getValue();
        User user = null == object ? userService.getUserInfo(ticket) : (User) object;
        ModelAndView mv;
        if (user != null) {
            mv = new ModelAndView("redirect:/user/search");
        } else
            mv = new ModelAndView("common/login");
        return mv;
    }

    @RequestMapping("/check")
    public ModelAndView check(User user, HttpServletRequest request, HttpServletResponse response) {
        if (!userService.exists(user)) {
            ModelAndView mv = new ModelAndView("common/login");
            mv.addObject("err", "用户名或密码错误");
            return mv;
        }

        //Session
        user = userService.select(user);
        WebUtils.setSessionAttribute(request, Configs.SESSION_USER_ATTR, user);

        //Cookie
        String value = WebUtils.getSessionId(request);
        Cookie cookie = new Cookie(Configs.COOKIE_NAME, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        Cookie jsessionid = new Cookie("jsessionid", "jsessionid");
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(jsessionid);

        //Set Ticket
        LoginTicket loginTicket = new LoginTicket(user, value);
        userService.insertIfNotNull(loginTicket);

        //Redirect
        String url = request.getRequestURI();
        ModelAndView mv;
        if (url.contains("/user/check")) {
            mv = new ModelAndView("redirect:/user/search");
            Pager pager = new Pager();
            mv.addObject("pager", pager);
        } else {
            mv = new ModelAndView("redirect:" + url);
            Pager pager = new Pager();
            mv.addObject("pager", pager);
        }
        return mv;
    }

}
