package com.my.example.dashboard.service;

import com.my.example.dashboard.model.domain.LoginTicket;
import com.my.example.dashboard.model.domain.User;

import java.util.List;

/**
 * Date:17/6/5
 * Time:上午11:16
 *
 * @author yongquan.wen
 */
public interface UserService {

    List<User> selectAll();

    boolean exists(User user);

    User select(User user);

    User getUserInfo(String ticket);

    boolean insertIfNotNull(LoginTicket loginTicket);
}
