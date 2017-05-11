package com.my.example.dashboard.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page<T>{
    private int page = 1;
    private int pageSize = 50;
    private int totalPage = 0;
    private int total = 0;
    private List<T> rows = Collections.emptyList();

    public Page() {
    }

    public Page(int page, int pageSize, int total) {
        this(new ArrayList<T>(), page, pageSize, total);
    }

    public Page(List<T> rows, int page, int pageSize, int total) {
        this.rows = rows;
        this.pageSize = pageSize;
        this.total = total;
        this.totalPage = (total + pageSize - 1) / pageSize;
        this.page = page > totalPage ? totalPage : page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
