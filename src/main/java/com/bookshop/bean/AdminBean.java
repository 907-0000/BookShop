package com.bookshop.bean;

/**
 * 封装数据
 */
public class AdminBean {
    private Integer adminId;
    private String account;
    private String pass;
    private String nickName;
    private Integer delFlag;

    //getter 和 setter
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "AdminBean{" +
                "adminId=" + adminId +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", nickName='" + nickName + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
