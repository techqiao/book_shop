package com.wjq.domain;

import javax.persistence.Embeddable;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 10:19
 * <p>@author : wjq
 */
@Embeddable
public class Address {
    private String provice;
    private String city;
    private String area;
    private String address;
    private String zipCode;

    public String getProvice() {
        return provice;
    }

    public Address setProvice(String provice) {
        this.provice = provice;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getArea() {
        return area;
    }

    public Address setArea(String area) {
        this.area = area;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
}
