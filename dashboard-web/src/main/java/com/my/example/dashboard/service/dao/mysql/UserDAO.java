package com.my.example.dashboard.service.dao.mysql;

import com.my.example.dashboard.model.domain.User;

import java.util.List;

public interface UserDAO {
    List<User> selectAll();
}