package com.wjq.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 9:31
 * <p>@author : wjq
 */
@ApiModel
public class BookCondition {
    @ApiModelProperty(value = "门类名称")
    private String name;
    @ApiModelProperty(value = "门类ID")
    private Long categoryId;

    public String getName() {
        return name;
    }

    public BookCondition setName(String name) {
        this.name = name;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public BookCondition setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    @Override
    public String toString() {
        return "BookCondition{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
