package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.AND;
import static br.com.elementi.core.qtable.QOperator.BETWEEN;

import org.hibernate.Query;

public class QBetween extends QAbstract {

    private Integer firstValue;
    private Integer secondValue;

    public QBetween(String alias, String field, Integer firstValue, Integer secondValue) {
        super(alias, field, "", BETWEEN);
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public String parameterNameQuery() {
        return ":" + first() + AND.operator() + ":" + second();
    }

    @Override
    public void joinValue(Query query) {
        query.setParameter(first(), firstValue);
        query.setParameter(second(), secondValue);
    }

    private String first() {
        return parameterName() + "1";
    }

    private String second() {
        return parameterName() + "2";
    }

}
