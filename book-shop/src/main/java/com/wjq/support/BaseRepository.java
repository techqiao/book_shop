package com.wjq.support;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/17 10:13
 * <p>@author : wjq
 */
@NoRepositoryBean//Spring Data Jpa在启动时就不会去实例化BaseRepository这个接口
public interface BaseRepository<T,ID extends Serializable> extends PagingAndSortingRepository<T,ID> {
    boolean support(String modelType);
}
