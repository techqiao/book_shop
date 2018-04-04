package com.wjq.domain;

import com.wjq.enums.Sex;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 9:57
 * <p>@author : wjq
 */
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "age",columnDefinition = "Int(3)")
    private Integer age;
    @Column
    private String name;
    @Temporal(TemporalType.TIME)
    private Date birthDay;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Embedded
    private Address address;
    @ElementCollection
    private List<String> hobbies;
    @ElementCollection
    private List<Address> addresses;
    @OneToMany(mappedBy = "author")
    @OrderBy("book.name asc")
    private List<BookAuthor> books;
    @OneToOne
    private AuthorInfo authorInfo;
    @NotBlank
    private String email;

    public Long getId() {
        return id;
    }

    public Author setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Author setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Sex getSex() {
        return sex;
    }

    public Author setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public Author setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Author setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public Author setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Author setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }


    public List<BookAuthor> getBooks() {
        return books;
    }

    public Author setBooks(List<BookAuthor> books) {
        this.books = books;
        return this;
    }

    public AuthorInfo getAuthorInfo() {
        return authorInfo;
    }

    public Author setAuthorInfo(AuthorInfo authorInfo) {
        this.authorInfo = authorInfo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Author setEmail(String email) {
        this.email = email;
        return this;
    }
}
