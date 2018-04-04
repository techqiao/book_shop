package com.wjq.domain;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/18 11:16
 * <p>@author : wjq
 */
//@Entity
public class Ebook extends Book {
    private String type;

    public String getType() {
        return type;
    }

    public Ebook setType(String type) {
        this.type = type;
        return this;
    }
}
