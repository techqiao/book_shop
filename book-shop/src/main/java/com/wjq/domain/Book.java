package com.wjq.domain;

import javax.persistence.*;
import java.util.List;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 10:35
 * <p>@author : wjq
 */
@Entity
public class Book extends DomainImpl {
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> authors;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "info_category_info_id")
    private Category category;
    @Version
    private int version;

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Book setCategory(Category category) {
        this.category = category;
        return this;
    }

    public List<BookAuthor> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<BookAuthor> authors) {
        this.authors = authors;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Book setVersion(int version) {
        this.version = version;
        return this;
    }
}
