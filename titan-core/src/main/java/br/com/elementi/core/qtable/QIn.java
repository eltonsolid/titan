package br.com.elementi.core.qtable;

import com.google.common.collect.Lists;

public class QIn extends QAbstract {

	public QIn(String alias, String field, Object... values) {
		super(alias, field, Lists.newArrayList(values), QOperator.IN);
	}
}
