package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.OR;

import org.hibernate.Query;

public class QOr implements QConstraint {

    public static final QOr POINT = new QOr(null);

    private QConstraint constraint;

    public QOr(QConstraint constraint) {
        this.constraint = constraint;
    }

    public void joinQuery(StringBuilder query) {
        constraint.joinQuery(query.append(OR.operator()));
    }

    public void joinValue(Query query) {
        constraint.joinValue(query);
    }

}
