package com.my.example.dashboard.common;

import java.util.List;

/**
 * Date:17/2/4
 * Time:下午4:11
 *
 * @author yongquan.wen@corp.elong.com
 */
public class Pager<T> {

    private static final int DEFAULT_SIZE = 20;
    private int page;
    private int pageSize;
    private List<T> pagerData;
    private int totalCount;
    private int totalPages;

    public Pager() {
        this.page = 1;
        this.pageSize = DEFAULT_SIZE;
        this.totalCount = 0;
        this.totalPages = 0;
    }

    public Pager(int page, int pageSize, int totalCount) {
        this();
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.initTotalPages(totalCount, pageSize);
    }

    public Pager(int page, int pageSize, int totalCount, List<T> pagerData) {
        this(page, pageSize, totalCount);
        this.pagerData = pagerData;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getpagerData() {
        return this.pagerData;
    }

    public void setpagerData(List<T> pagerData) {
        this.pagerData = pagerData;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.initTotalPages(totalCount, this.pageSize);
    }

    public int getTotalPages() {
        if (this.totalCount < 1 | this.pageSize < 1) {
            return 0;
        } else {
            if (this.totalPages < 1) {
                this.initTotalPages(this.totalCount, this.pageSize);
            }

            return this.totalPages;
        }
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    private void initTotalPages(int totalCount, int pageSize) {
        if (totalCount > 0 && pageSize > 0) {
            this.totalPages = totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;
        }

    }

}
