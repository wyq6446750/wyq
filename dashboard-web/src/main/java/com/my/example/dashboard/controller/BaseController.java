package com.my.example.dashboard.controller;

import com.my.example.dashboard.common.Constants;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public <T> Map buildRequestMap(HttpServletRequest httpServletRequest, T t) {
        Map requestMap = new HashMap();
        requestMap.put("request", t);
        requestMap.put("page", NumberUtils.toInt(httpServletRequest.getParameter("page"), 1));
        requestMap.put("pageSize", NumberUtils.toInt(httpServletRequest.getParameter("pageSize"), Constants.PAGE_SIZE));
        return requestMap;
    }

    public String getRemoteIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
