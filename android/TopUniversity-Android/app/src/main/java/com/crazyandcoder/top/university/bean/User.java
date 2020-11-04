package com.crazyandcoder.top.university.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private Integer id;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户登录密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户性别
     * 0：未知
     * 1：男
     * 2：女
     */
    private Integer userGender;

    /**
     * 用户年龄
     */
    private Integer userAge;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户所在城市
     */
    private String userCity;
    /**
     * 用户简介
     */
    private String userIntroduction;

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.userPhone);
        dest.writeString(this.userEmail);
        dest.writeString(this.userName);
        dest.writeString(this.userId);
        dest.writeString(this.userPassword);
        dest.writeString(this.userNickName);
        dest.writeString(this.userAvatar);
        dest.writeValue(this.userGender);
        dest.writeValue(this.userAge);
        dest.writeString(this.userAddress);
        dest.writeString(this.userCity);
        dest.writeString(this.userIntroduction);
    }

    protected User(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userPhone = in.readString();
        this.userEmail = in.readString();
        this.userName = in.readString();
        this.userId = in.readString();
        this.userPassword = in.readString();
        this.userNickName = in.readString();
        this.userAvatar = in.readString();
        this.userGender = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userAge = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userAddress = in.readString();
        this.userCity = in.readString();
        this.userIntroduction = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
