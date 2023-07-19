package com.mtx.model.vo;


import com.mtx.model.entity.Checkout;
import com.mtx.model.entity.Userlist;
import lombok.Data;

@Data
public class CheckoutBean extends Checkout {
    private Userlist userlist;
}
