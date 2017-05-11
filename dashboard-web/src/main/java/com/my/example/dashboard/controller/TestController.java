package com.my.example.dashboard.controller;

import com.my.example.dashboard.common.Pager;
import com.my.example.dashboard.model.domain.Test;
import com.my.example.dashboard.service.dao.mysql.TestDAO;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Date:17/2/4
 * Time:下午3:35
 *
 * @author yongquan.wen
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestDAO testDAO;

    @RequestMapping("search")
    public ModelAndView index(@RequestParam(name = "ajax", defaultValue = "false", required = false) boolean ajax, Test test) {
        ModelAndView modelAndView = new ModelAndView(ajax ? "test/coupon-list" : "test/search");
        if (ajax) {
            List<Test> list = testDAO.selectAll();
            Pager<Test> pager = new Pager<>(1, 20, list.size(), list);
            modelAndView.addObject("pager", pager);
        } else {
            Pager pager = new Pager();
            modelAndView.addObject("pager", pager);
        }
        return modelAndView;
    }

}
