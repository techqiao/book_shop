package com.wjq.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/18 11:54
 * <p>@author : wjq
 */
@NoRepositoryBean
public interface BookShopRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
