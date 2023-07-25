package com.yc.usersys.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Integer uid;//编号
    private String uname;//用户名
    private String upwd;//密码
    private String tel;//手机号
    private String pics;//图片
    private Integer state;//状态 1，可用 2， 禁用

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", tel='" + tel + '\'' +
                ", pics='" + pics + '\'' +
                ", state=" + state +
                '}';
    }
}

