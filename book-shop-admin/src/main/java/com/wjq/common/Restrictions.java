package com.wjq.common;

import com.wjq.common.Criterion.MatchMode;
import com.wjq.common.Criterion.Operator;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.JoinType;
import java.util.Collection;

/**
 * 条件构造器
 * 用于创建条件表达式
 */
public class Restrictions {

    /**
     * 不为空
     *
     * @param fieldName : 匹配字段
     * @return SimpleExpression
     */
    public static SimpleExpression isNotEmpty(String fieldName) {
        return new SimpleExpression(fieldName, Criterion.Operator.ISNOTEMPTY);
    }

    /**
     * 不为空
     *
     * @param joinFieldName
     * @param fieldName     : 匹配字段
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression isNotEmpty(String joinFieldName, String fieldName, JoinType joinType) {
        return new SimpleExpression(joinFieldName, fieldName, Criterion.Operator.ISNOTEMPTY, joinType);
    }

    /**
     * 为空
     *
     * @param fieldName : 匹配字段
     * @return SimpleExpression
     */
    public static SimpleExpression isEmpty(String fieldName) {
        return new SimpleExpression(fieldName, Operator.ISEMPTY);
    }

    /**
     * 为空
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression isEmpty(String joinFieldName, String fieldName, JoinType joinType) {
        return new SimpleExpression(joinFieldName, fieldName, Operator.ISEMPTY, joinType);
    }

    /**
     * 为空
     *
     * @param fieldName : 匹配字段
     * @return SimpleExpression
     */
    public static SimpleExpression isNull(String fieldName) {
        return new SimpleExpression(fieldName, Operator.ISNULL);
    }

    /**
     * 为空
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression isNull(String joinFieldName, String fieldName, JoinType joinType) {
        return new SimpleExpression(joinFieldName, fieldName, Operator.ISNULL, joinType);
    }

    /**
     * 不为空
     *
     * @param fieldName : 匹配字段
     * @return SimpleExpression
     */
    public static SimpleExpression isNotNull(String fieldName) {
        return new SimpleExpression(fieldName, Operator.ISNOTNULL);
    }

    /**
     * 不为空
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression isNotNull(String joinFieldName, String fieldName, JoinType joinType) {
        return new SimpleExpression(joinFieldName, fieldName, Operator.ISNOTNULL, joinType);
    }

    /**
     * 等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression eq(String fieldName, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.EQ);
    }

    /**
     * 等于
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression eq(String joinFieldName, String fieldName, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.EQ, joinType);
    }

    /**
     * 等于 （函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression eq(Projection projection, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.EQ);
    }

    /**
     * 等于 （函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression eq(String joinFieldName, Projection projection, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.EQ, joinType);
    }

    /**
     * 不等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression ne(String fieldName, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.NE);
    }

    /**
     * 不等于
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression ne(String joinFieldName, String fieldName, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.NE, joinType);
    }

    /**
     * 不等于（函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression ne(Projection projection, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.NE);
    }

    /**
     * 不等于（函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression ne(String joinFieldName, Projection projection, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.NE, joinType);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression like(String fieldName, String value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.LIKE);
    }

    /**
     * 模糊匹配
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression like(String joinFieldName, String fieldName, String value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.LIKE, joinType);
    }

    /**
     * 模糊匹配 （函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression like(Projection projection, String value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.LIKE);
    }

    /**
     * 模糊匹配 （函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression like(String joinFieldName, Projection projection, String value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.LIKE, joinType);
    }

    /**
     * 自定义模式模糊匹配
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @param matchMode : 匹配方式(MatchMode.START\END\ANYWHERE)
     * @return SimpleExpression
     */
    public static SimpleExpression like(String fieldName, String value,
                                        MatchMode matchMode) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, matchMode, Operator.LIKE);
    }

    /**
     * 自定义模式模糊匹配
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param matchMode     : 匹配方式(MatchMode.START\END\ANYWHERE)
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression like(String joinFieldName, String fieldName, String value,
                                        MatchMode matchMode, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, matchMode, Operator.LIKE, joinType);
    }

    /**
     * 自定义模式模糊匹配（函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @param matchMode  : 匹配方式(MatchMode.START\END\ANYWHERE)
     * @return ProjectionExpression
     */
    public static ProjectionExpression like(Projection projection, String value,
                                            MatchMode matchMode) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), matchMode, Operator.LIKE);
    }

    /**
     * 自定义模式模糊匹配（函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param matchMode     : 匹配方式(MatchMode.START\END\ANYWHERE)
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression like(String joinFieldName, Projection projection, String value,
                                            MatchMode matchMode, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), matchMode, Operator.LIKE, joinType);
    }

    /**
     * 大于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression gt(String fieldName, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.GT);
    }

    /**
     * 大于
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression gt(String joinFieldName, String fieldName, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.GT, joinType);
    }

    /**
     * 大于（函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression gt(Projection projection, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.GT);
    }

    /**
     * 大于（函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression gt(String joinFieldName, Projection projection, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.GT, joinType);
    }

    /**
     * 小于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression lt(String fieldName, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.LT);
    }

    /**
     * 小于
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression lt(String joinFieldName, String fieldName, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.LT, joinType);
    }

    /**
     * 小于（函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression lt(Projection projection, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.LT);
    }

    /**
     * 小于（函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression lt(String joinFieldName, Projection projection, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.LT, joinType);
    }

    /**
     * 小于等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression lte(String fieldName, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.LTE);
    }

    /**
     * 小于等于
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression lte(String joinFieldName, String fieldName, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.LTE, joinType);
    }

    /**
     * 小于等于（函数条件查询）
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression lte(Projection projection, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.LTE);
    }

    /**
     * 小于等于（函数条件查询）
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression lte(String joinFieldName, Projection projection, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.LTE, joinType);
    }

    /**
     * 大于等于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return SimpleExpression
     */
    public static SimpleExpression gte(String fieldName, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, Operator.GTE);
    }

    /**
     * 大于等于
     *
     * @param joinFieldName 多表连接的字段
     * @param fieldName     : 匹配字段
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return SimpleExpression
     */
    public static SimpleExpression gte(String joinFieldName, String fieldName, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(joinFieldName, fieldName, value, Operator.GTE, joinType);
    }

    /**
     * 大于等于
     *
     * @param projection : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value      : 匹配值
     * @return ProjectionExpression
     */
    public static ProjectionExpression gte(Projection projection, Object value) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(projection.getCol(), value, projection.getType(), Operator.GTE);
    }

    /**
     * 大于等于
     *
     * @param joinFieldName 多表连接的字段
     * @param projection    : Projection查询条件(Projections.MAX\SUM\AVG...)
     * @param value         : 匹配值
     * @param joinType      连接类型
     * @return ProjectionExpression
     */
    public static ProjectionExpression gte(String joinFieldName, Projection projection, Object value, JoinType joinType) {
        if (StringUtils.isEmpty(value)) return null;
        return new ProjectionExpression(joinFieldName, projection.getCol(), value, projection.getType(), Operator.GTE, joinType);
    }


    /**
     * 或者
     *
     * @param criterions 条件接口
     * @return LogicalExpression
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression(criterions, Operator.OR);
    }


    /**
     * 区间
     *
     * @param column : 匹配字段
     * @param val1   左区间
     * @param val2   右区间
     * @return LogicalExpression
     */
    public static LogicalExpression between(String column, Object val1, Object val2) {
        if (StringUtils.isEmpty(val1) || StringUtils.isEmpty(val2)) return null;
        return new LogicalExpression(column, val1, val2, Operator.BETWEEN);
    }


    /**
     * 包含于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return LogicalExpression
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value) {
        if (StringUtils.isEmpty(value)) return null;
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, Operator.EQ);
            i++;
        }
        return new LogicalExpression(ses, Operator.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName : 匹配字段
     * @param value     : 匹配值
     * @return LogicalExpression
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression notIn(String fieldName, Collection value) {
        if (StringUtils.isEmpty(value)) return null;
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, Operator.NE);
            i++;
        }
        return new LogicalExpression(ses, Operator.AND);
    }

}
