package com.wjq.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 11:17
 * <p>@author : wjq
 */
@Entity
public class BookAuthor {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Author author;

    public Long getId() {
        return id;
    }

    public BookAuthor setId(Long id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public BookAuthor setBook(Book book) {
        this.book = book;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public BookAuthor setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
