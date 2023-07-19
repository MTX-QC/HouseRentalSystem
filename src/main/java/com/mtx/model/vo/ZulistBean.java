package com.mtx.model.vo;


import com.mtx.model.entity.Userlist;
import com.mtx.model.entity.Zulist;

public class ZulistBean extends Zulist {
    private Userlist userlist;

    public Userlist getUserlist() {
        return userlist;
    }

    public void setUserlist(Userlist userlist) {
        this.userlist = userlist;
    }
}
