package com.my.example.dashboard.service;

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

}
