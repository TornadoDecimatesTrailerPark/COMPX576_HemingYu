package com.bean;

import cn.bmob.v3.BmobObject;



public class Hot extends BmobObject {
    private String name;
    private Integer number;

    public Hot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
