package com.wjq.common;

import javax.persistence.criteria.*;

/**
 * 函数条件表达式
 */
public class ProjectionExpression extends SimpleExpression implements Criterion {

    /**
     * 函数条件类型
     */
    private Projection projection;

    ProjectionExpression(String fieldName, Object value, Projection projection, Operator operator) {
        super(fieldName, value, operator);
        this.projection = projection;
    }

    ProjectionExpression(String joinFieldName, String fieldName, Object value, Projection projection, Operator operator, JoinType joinType) {
        super(joinFieldName, fieldName, value, operator, joinType);
        this.projection = projection;
    }

    ProjectionExpression(String fieldName, Object value, Projection projection, MatchMode matchMode, Operator operator) {
        super(fieldName, value, matchMode, operator);
        this.projection = projection;
    }

    ProjectionExpression(String joinFieldName, String fieldName, Object value, Projection projection, MatchMode matchMode, Operator operator, JoinType joinType) {
        super(joinFieldName, fieldName, value, matchMode, operator, joinType);
        this.projection = projection;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        Path expression = getPath(root);
        switch (projection) {
            case LENGTH:
                return getBuilder(builder, builder.length(expression), super.getValue(), super.getMatchMode());
            case MAX:
                return getBuilder(builder, builder.max(expression), super.getValue(), super.getMatchMode());
            case SUM:
                return getBuilder(builder, builder.sum(expression), super.getValue(), super.getMatchMode());
            case MIN:
                return getBuilder(builder, builder.min(expression), super.getValue(), super.getMatchMode());
            case AVG:
                return getBuilder(builder, builder.avg(expression), super.getValue(), super.getMatchMode());
            case COUNT:
                return getBuilder(builder, builder.count(expression), super.getValue(), super.getMatchMode());
            default:
                return null;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Predicate getBuilder(CriteriaBuilder builder, Expression expression, Object obj, MatchMode matchMode) {
        switch (super.getOperator()) {
            case EQ:
                return builder.equal(expression, super.getValue());
            case NE:
                return builder.notEqual(expression, super.getValue());
            case LIKE:
                return getPredicate(builder, expression);
            case LT:
                return builder.lessThan(expression, (Comparable) super.getValue());
            case GT:
                return builder.greaterThan(expression, (Comparable) super.getValue());
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) super.getValue());
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) super.getValue());
            default:
                return null;
        }
    }


    public Projection getProjection() {
        return projection;
    }

}
