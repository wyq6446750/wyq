package com.my.example.dashboard.service.impl;

import com.my.example.dashboard.model.domain.LoginTicket;
import com.my.example.dashboard.model.domain.User;
import com.my.example.dashboard.service.UserService;
import com.my.example.dashboard.service.dao.mysql.LoginTicketDAO;
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

    @Resource
    private LoginTicketDAO loginTicketDAO;

    @Override
    public List<User> selectAll() {
        return userDAO.selectAll();
    }

    @Override
    public boolean exists(User user) {
        return userDAO.select(user) != null;
    }

    @Override
    public User select(User user) {
        return userDAO.select(user);
    }

    @Override
    public User getUserInfo(String ticket) {
        if (ticket == null || ticket.equals("")) {
            return null;
        }
        LoginTicket loginTicket = loginTicketDAO.selectByTicket(ticket);
        if (loginTicket == null)
            return null;
        User user = new User();
        user.setUserName(loginTicket.getUserName());
        user.setPassword(loginTicket.getPassword());
        user = userDAO.select(user);
        return user;

    }

    @Override
    public boolean insertIfNotNull(LoginTicket loginTicket) {
        if (loginTicketDAO.selectByTicket(loginTicket.getTicket()) != null) {
            return true;
        }
        loginTicketDAO.insert(loginTicket);
        return true;
    }
}
