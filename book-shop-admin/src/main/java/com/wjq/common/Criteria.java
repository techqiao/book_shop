package com.wjq.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description : 定义一个查询条件容器
 * <p>Date : 2018/2/3 14:57
 * <p>@author : wjq
 */
@Data
public class Criteria<T> implements Specification<T> {
    private List<Criterion> criterions = new ArrayList<>();
    /**
     * 倒序查询条件
     */
    private String orderByDESC;
    /**
     * 升序查询条件
     */
    private String orderByASC;
    /**
     * group查询条件
     */
    private String groupBy;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (!StringUtils.isEmpty(orderByASC))
            query.orderBy(cb.desc(root.get(getOrderByASC())));
        if (!StringUtils.isEmpty(orderByDESC))
            query.orderBy(cb.desc(root.get(getOrderByDESC())));
        if (!StringUtils.isEmpty(groupBy))
            query.groupBy(root.get(getGroupBy()));
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (Criterion c : criterions) {
                predicates.add(c.toPredicate(root, query, cb));
            }
            if (predicates.size() > 0) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return cb.conjunction();
    }
    /**
     * 增加简单条件表达式
     *
     * @param criterion 条件接口
     */
    public void add(Criterion criterion) {
        if (criterion != null) {
            criterions.add(criterion);
        }
    }

    /**
     * 降序
     *
     * @param fieldName 字段名称
     */
    public void orderByDESC(String fieldName) {
        setOrderByDESC(fieldName);
    }

    /**
     * 升序
     *
     * @param fieldName 字段名称
     */
    public void orderByASC(String fieldName) {
        setOrderByASC(fieldName);
    }

    /**
     * 分组
     *
     * @param fieldName 字段名称
     */
    public void groupBy(String fieldName) {
        setGroupBy(fieldName);
    }
}
