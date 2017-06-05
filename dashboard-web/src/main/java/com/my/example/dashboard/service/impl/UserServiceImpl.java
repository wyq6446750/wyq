package com.my.example.dashboard.service.impl;

import com.my.example.dashboard.model.domain.User;
import com.my.example.dashboard.service.UserService;
import com.my.example.dashboard.service.dao.mysql.UserDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Date:17/6/5
 * Time:上午11:18
 *
 * @author yongquan.wen
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public List<User> selectAll() {
        return userDAO.selectAll();
    }



}
