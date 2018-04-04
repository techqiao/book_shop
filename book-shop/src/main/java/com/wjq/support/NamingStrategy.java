package com.wjq.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 10:19
 * <p>@author : wjq
 */
public class NamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
    /**
     * @param stringForm 生成的字段
     * @param buildingContext
     * @return
     */
    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier("info_"+stringForm, buildingContext);
    }
}
