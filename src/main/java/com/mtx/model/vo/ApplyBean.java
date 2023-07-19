package com.mtx.model.vo;


import com.mtx.model.entity.Apply;
import com.mtx.model.entity.Userlist;
import lombok.Data;

@Data
public class ApplyBean extends Apply {
    private Userlist userlist;

    public Userlist getUserlist() {
        return userlist;
    }

    public void setUserlist(Userlist userlist) {
        this.userlist = userlist;
    }
}
