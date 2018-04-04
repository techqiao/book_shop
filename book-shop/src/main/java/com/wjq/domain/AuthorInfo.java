package com.wjq.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 11:35
 * <p>@author : wjq
 */
@Entity
public class AuthorInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String school;

    @OneToOne(mappedBy = "authorInfo")
    private Author author;

    public Long getId() {
        return id;
    }

    public String getSchool() {
        return school;
    }

    public AuthorInfo setSchool(String school) {
        this.school = school;
        return this;
    }

    public AuthorInfo setId(Long id) {
        this.id = id;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public AuthorInfo setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
