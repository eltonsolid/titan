package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.EQUAL;

public class QEqual extends QAbstract {

	public QEqual(String alias, String field, Object value) {
		super(alias, field, value, EQUAL);
	}

}
