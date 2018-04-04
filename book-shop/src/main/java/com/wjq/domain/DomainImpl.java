package com.wjq.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/16 11:52
 * <p>@author : wjq
 */
@MappedSuperclass
public class DomainImpl {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public DomainImpl setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DomainImpl setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
