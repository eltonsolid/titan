package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.GREATER;

public class QGt extends QAbstract {

	public QGt(String alias, String field, Object value) {
		super(alias, field, value, GREATER);
	}

}
