package com.mtx.model.vo;
import lombok.Data;

@Data
public class SearchBean {
    private String search;  //关键词
    private String select;  //下拉列表选中的值
    private Integer cur;    //当前页码
    private Integer size;   //显示每页的数量
    private String username;
    private Integer user_id;
    private String date;
    private String content;
}
