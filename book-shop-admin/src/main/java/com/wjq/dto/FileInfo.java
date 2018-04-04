package com.wjq.dto;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 13:14
 * <p>@author : wjq
 */
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public FileInfo setPath(String path) {
        this.path = path;
        return this;
    }
}
