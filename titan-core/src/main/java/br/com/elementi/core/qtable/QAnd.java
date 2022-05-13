package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.AND;

import org.hibernate.Query;

public class QAnd implements QConstraint {

	private QConstraint constraint;

	public QAnd(QConstraint constraint) {
		this.constraint = constraint;
	}

	@Override
	public void joinQuery(StringBuilder query) {
		constraint.joinQuery(query.append(AND.operator()));
	}

	@Override
	public void joinValue(Query query) {
		constraint.joinValue(query);
	}

}
