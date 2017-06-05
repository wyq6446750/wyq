package com.my.example.dashboard.interceptor;

import com.my.example.dashboard.model.config.Configs;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Date:17/2/3
 * Time:下午3:18
 *
 * @author yongquan.wen
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object object = WebUtils.getSessionAttribute(httpServletRequest, Configs.SESSION_USER_ATTR);
        String value = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("utoken", value);
        cookie.setMaxAge(-1);
        cookie.setComment("comment");
        httpServletResponse.addCookie(cookie);

        return true;

    }
}
