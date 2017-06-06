package com.my.example.dashboard.model.domain;

import java.util.Date;

public class LoginTicket {

    private Long id;

    private String ticket;

    private String userName;

    private String password;

    private Date timestamp;

    public LoginTicket(){}

    public LoginTicket(User user) {
        this(user, null);
    }

    public LoginTicket(User user, String ticket) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.ticket = ticket;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }



}