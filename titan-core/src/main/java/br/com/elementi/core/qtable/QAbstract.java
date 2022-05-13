package br.com.elementi.core.qtable;

import br.com.elementi.core.tools.Regex;
import org.hibernate.Query;

import java.util.Collection;

public class QAbstract implements QConstraint {

    private String alias;
    private String field;
    private Object value;
    private QOperator operator;

    public QAbstract(String alias, String field, Object value, QOperator operator) {
        this.alias = alias;
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public void joinQuery(StringBuilder query) {
        query.append(alias + "." + field + operator.operator() + parameterNameQuery());
    }

    protected String parameterNameQuery() {
        return value instanceof Iterable ? "(:" + parameterName() + ")" : ":" + parameterName();
    }

    @Override
    public void joinValue(Query query) {
        if (value instanceof Iterable) {
            query.setParameterList(parameterName(), (Collection<?>) value);
        } else {
            query.setParameter(parameterName(), value);
        }
    }

    protected String parameterName() {
        return Regex.replaceDotForUnderscor(field) + "$" + value;
    }

}
