package com.my.example.dashboard.service.dao.mysql;

import com.my.example.dashboard.model.domain.Test;

import java.util.List;

public interface TestDAO {
    List<Test> selectAll();
}