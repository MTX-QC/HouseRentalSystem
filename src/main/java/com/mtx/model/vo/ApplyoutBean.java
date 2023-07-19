package com.mtx.model.vo;


import com.mtx.model.entity.Applyout;
import com.mtx.model.entity.Userlist;
import lombok.Data;

@Data
public class ApplyoutBean extends Applyout {
    private Userlist userlist;

}
