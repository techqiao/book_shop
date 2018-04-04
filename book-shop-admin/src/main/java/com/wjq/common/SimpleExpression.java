package com.wjq.common;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.*;

/**
 * 简单条件表达式
 */
public class SimpleExpression implements Criterion {

    /**
     * 连接的对象字段
     */
    private String joinFieldName;

    /**
     * 属性名
     */
    private String fieldName;

    /**
     * 对应值
     */
    private Object value;

    /**
     * 计算符
     */
    private Operator operator;

    /**
     * like匹配方式
     */
    private MatchMode matchMode;

    /**
     * 多表连接方式
     */
    private JoinType joinType;

    protected SimpleExpression(String fieldName, Operator operator) {
        this.fieldName = fieldName;
        this.operator = operator;
    }

    protected SimpleExpression(String joinFieldName, String fieldName, Operator operator, JoinType joinType) {
        this.joinFieldName = joinFieldName;
        this.fieldName = fieldName;
        this.operator = operator;
        this.joinType = joinType;
    }

    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    protected SimpleExpression(String joinFieldName, String fieldName, Object value, Operator operator, JoinType joinType) {
        this.joinFieldName = joinFieldName;
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.joinType = joinType;
    }

    protected SimpleExpression(String fieldName, Object value, MatchMode matchMode, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.matchMode = matchMode;
    }

    public SimpleExpression(String joinFieldName, String fieldName, Object value, MatchMode matchMode, Operator operator, JoinType joinType) {
        this.joinFieldName = joinFieldName;
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
        this.matchMode = matchMode;
        this.joinType = joinType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }

    public MatchMode getMatchMode() {
        return matchMode;
    }

    public String getJoinFieldName() {
        return joinFieldName;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        Path expression = getPath(root);

        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                if (matchMode == null) {
                    return builder.like((Expression<String>) expression.as(String.class), "%" + value + "%");
                } else {
                    return getPredicate(builder, expression);
                }
            case LT:
                return builder.lessThan(expression, (Comparable) value);
            case GT:
                return builder.greaterThan(expression, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable) value);
            case ISNOTNULL:
                return builder.isNotNull(expression);
            case ISNULL:
                return builder.isNull(expression);
            case ISEMPTY:
                return builder.isEmpty(expression);
            case ISNOTEMPTY:
                return builder.isNotEmpty(expression);
            default:
                return null;
        }
    }

    protected Predicate getPredicate(CriteriaBuilder builder, Expression<String> expression) {
        switch (matchMode) {
            case START:
                return builder.like(expression, value + "%");
            case END:
                return builder.like(expression, "%" + value);
            case ANYWHERE:
                return builder.like(expression, "%" + value + "%");
            default:
                return builder.like(expression, "%" + value + "%");
        }
    }

    protected Path getPath(Root<?> root) {
        Path expression = null;
        if (fieldName.contains(".")) {
            String[] names = StringUtils.split(fieldName, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            if (joinType == null) {
                expression = root.get(fieldName);
            } else {
                switch (joinType) {
                    case INNER:
                        expression = root.join(joinFieldName, JoinType.INNER).get(fieldName);
                        break;
                    case LEFT:
                        expression = root.join(joinFieldName, JoinType.LEFT).get(fieldName);
                        break;
                    case RIGHT:
                        expression = root.join(joinFieldName, JoinType.RIGHT).get(fieldName);
                        break;
                    default:
                        break;
                }
            }
        }
        return expression;
    }
}
