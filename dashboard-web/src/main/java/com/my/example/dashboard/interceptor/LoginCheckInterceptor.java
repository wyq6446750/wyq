package com.my.example.dashboard.interceptor;

import com.my.example.dashboard.model.config.Configs;
import com.my.example.dashboard.model.domain.User;
import com.my.example.dashboard.service.UserService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date:17/2/3
 * Time:下午3:18
 *
 * @author yongquan.wen
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object object = WebUtils.getSessionAttribute(httpServletRequest, Configs.SESSION_USER_ATTR);
        Cookie cookie = WebUtils.getCookie(httpServletRequest, Configs.COOKIE_NAME);
        String ticket = null == cookie ? null : WebUtils.getCookie(httpServletRequest, Configs.COOKIE_NAME).getValue();
        User user = null == object ? userService.getUserInfo(ticket) : (User) object;
        if (user != null) {
            WebUtils.setSessionAttribute(httpServletRequest, Configs.SESSION_USER_ATTR, user);
            return true;
        }
        httpServletResponse.sendRedirect("/user/login");

        return false;

    }
}
