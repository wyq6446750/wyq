package com.my.example.dashboard.service.dao.mysql;

import com.my.example.dashboard.model.domain.LoginTicket;

public interface LoginTicketDAO {

    int deleteByPrimaryKey(Long id);

    int insert(LoginTicket record);

    LoginTicket selectByTicket(String ticket);

}