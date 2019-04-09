package com.jincou.model;

import java.util.Date;

public class Person {

    public Person(String userName, String myCcity, String passWord, Date currTime) {
        this.userName = userName;
        this.myCcity = myCcity;
        this.passWord = passWord;
        this.currTime = currTime;
    }

    private String userName;
    private String myCcity;
    private String passWord;
    private Date currTime;

    public Date getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMyCcity() {
        return myCcity;
    }

    public void setMyCcity(String myCcity) {
        this.myCcity = myCcity;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
