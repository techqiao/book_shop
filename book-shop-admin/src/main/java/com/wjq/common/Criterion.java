package com.wjq.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * <p>Description : 条件接口，用于提供条件表达式接口
 * <p>Date : 2018/2/3 15:08
 * <p>@author : wjq
 */
public interface Criterion {
    enum Operator {
        EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR, BETWEEN, ISNULL, ISNOTNULL, ISEMPTY, ISNOTEMPTY
    }

    enum MatchMode {
        START, END, ANYWHERE
    }

    enum Projection {
        MAX, MIN, AVG, LENGTH, SUM, COUNT
    }

    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
