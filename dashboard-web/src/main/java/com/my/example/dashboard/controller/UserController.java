package com.my.example.dashboard.controller;

import com.my.example.dashboard.common.Pager;
import com.my.example.dashboard.model.domain.User;
import com.my.example.dashboard.service.UserService;
import com.my.example.dashboard.service.dao.mysql.UserDAO;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/search")
    public ModelAndView index(@RequestParam(name = "ajax", defaultValue = "false", required = false) boolean ajax, User test) {
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

}
