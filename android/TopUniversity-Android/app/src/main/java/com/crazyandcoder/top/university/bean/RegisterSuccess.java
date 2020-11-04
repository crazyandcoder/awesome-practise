package com.crazyandcoder.top.university.bean;

public class RegisterSuccess {

    private String userName;

    public RegisterSuccess(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RegisterSuccess{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
