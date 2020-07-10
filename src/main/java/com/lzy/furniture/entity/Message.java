package com.lzy.furniture.entity;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String messInfo;
    private String messTime;
    private Integer userId;
    @Transient
    private String userName;

    private String ip;
    private Integer state;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messInfo='" + messInfo + '\'' +
                ", messTime='" + messTime + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", state=" + state +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessInfo() {
        return messInfo;
    }

    public void setMessInfo(String messInfo) {
        this.messInfo = messInfo;
    }

    public String getMessTime() {
        return messTime;
    }

    public void setMessTime(String messTime) {
        this.messTime = messTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
