package br.com.elementi.core.qtable;

import com.google.common.collect.Lists;
import org.hibernate.Query;

import java.util.LinkedList;

import static br.com.elementi.core.qtable.QOperator.OR;

/**
 * Created by eltonsolid on 27/12/16.
 */
public class QOr2 implements QConstraint {

    private LinkedList<QConstraint> constraints;

    public QOr2() {
        constraints = Lists.newLinkedList();
    }

    public QOr2 add(QConstraint constraint) {
        constraints.add(constraint);
        return this;
    }


    @Override
    public void joinQuery(StringBuilder query) {
        query.append(OR.operator()).append("(");
        constraints.forEach(c -> c.joinQuery(query));
        query.append(")");
    }

    @Override
    public void joinValue(Query query) {

    }
}
