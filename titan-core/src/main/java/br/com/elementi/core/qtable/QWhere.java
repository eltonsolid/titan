package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.WHERE;

import org.hibernate.Query;

public class QWhere implements QConstraint {

	private QConstraint constraint;

	public QWhere(QConstraint constraint) {
		this.constraint = constraint;
	}

	public void joinQuery(StringBuilder query) {
		constraint.joinQuery(query.append(WHERE.operator()));
	}

	public void joinValue(Query query) {
		constraint.joinValue(query);
	}

}
