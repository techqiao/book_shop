package com.wjq.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 9:30
 * <p>@author : wjq
 */
@ApiModel
@Entity
@Table(name = "book_info")
public class BookInfo implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(value = "图书名称")
    private String name;
    @NotBlank
    private String context;
    @Column
    @Temporal(TemporalType.DATE)
    private Date publishDate;

    public interface BookListView {} ;

    public interface BookDetailView extends BookListView {} ;

    @JsonView(BookListView.class)
    public Long getId() {
        return id;
    }

    public BookInfo setId(Long id) {
        this.id = id;
        return this;
    }
    @JsonView(BookListView.class)
    public String getName() {
        return name;
    }

    public BookInfo setName(String name) {
        this.name = name;
        return this;
    }
    @JsonView(BookDetailView.class)
    public String getContext() {
        return context;
    }

    public BookInfo setContext(String context) {
        this.context = context;
        return this;
    }
    @JsonView(BookListView.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public BookInfo setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
