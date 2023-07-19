package com.mtx.model.vo;


import com.mtx.model.entity.User;

public class UserInfo extends User {
    private String idcard;
    private String phone;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
