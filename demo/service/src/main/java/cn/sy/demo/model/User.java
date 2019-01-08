package cn.sy.demo.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private Long id;

    private String name;

    private String idCard;

    private String idCardImgPath;

    private Integer status;

    private String phone;

    private Integer opVerifyStatus;

    private String tmallUserName;

    private Integer tmallVerifyStatus;

    private Integer finId;

    private String finName;

    private String finCreditId;

    private Integer finCreditStatus;

    private Date finCreditTime;

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardImgPath() {
        return idCardImgPath;
    }

    public void setIdCardImgPath(String idCardImgPath) {
        this.idCardImgPath = idCardImgPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOpVerifyStatus() {
        return opVerifyStatus;
    }

    public void setOpVerifyStatus(Integer opVerifyStatus) {
        this.opVerifyStatus = opVerifyStatus;
    }

    public String getTmallUserName() {
        return tmallUserName;
    }

    public void setTmallUserName(String tmallUserName) {
        this.tmallUserName = tmallUserName;
    }

    public Integer getTmallVerifyStatus() {
        return tmallVerifyStatus;
    }

    public void setTmallVerifyStatus(Integer tmallVerifyStatus) {
        this.tmallVerifyStatus = tmallVerifyStatus;
    }

    public Integer getFinId() {
        return finId;
    }

    public void setFinId(Integer finId) {
        this.finId = finId;
    }

    public String getFinName() {
        return finName;
    }

    public void setFinName(String finName) {
        this.finName = finName;
    }

    public String getFinCreditId() {
        return finCreditId;
    }

    public void setFinCreditId(String finCreditId) {
        this.finCreditId = finCreditId;
    }

    public Integer getFinCreditStatus() {
        return finCreditStatus;
    }

    public void setFinCreditStatus(Integer finCreditStatus) {
        this.finCreditStatus = finCreditStatus;
    }

    public Date getFinCreditTime() {
        return finCreditTime;
    }

    public void setFinCreditTime(Date finCreditTime) {
        this.finCreditTime = finCreditTime;
    }
}