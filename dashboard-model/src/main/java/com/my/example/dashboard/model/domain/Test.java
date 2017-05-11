package com.my.example.dashboard.model.domain;

import java.util.Date;

/**
 * Date:17/2/3
 * Time:下午2:40
 *
 * @author yongquan.wen
 */
public class Test {

    private Long id;

    private String name;

    private Integer age;

    private Date timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
