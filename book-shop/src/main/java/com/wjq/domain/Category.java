package com.wjq.domain;

import javax.persistence.*;
import java.util.List;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 9:34
 * <p>@author : wjq
 */
@Table(name = "info_Category")
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Basic
    @Column(name="info_name",length = 12,nullable = true,unique = true)
    private String name;
    @Transient
    private String xyz;
    @OneToMany(mappedBy = "category",orphanRemoval =false,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public String getXyz() {
        return xyz;
    }

    public Category setXyz(String xyz) {
        this.xyz = xyz;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Category setBooks(List<Book> books) {
        this.books = books;
        return this;
    }
}