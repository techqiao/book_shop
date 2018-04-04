package com.wjq.support;


import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/17 9:06
 * <p>@author : wjq
 */
public class BookShopRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID>{


    public BookShopRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public <S extends T> S save(S entity) {
        System.out.println(entity.getClass().getSimpleName());
        return super.save(entity);
    }
}
