package br.com.elementi.core.qtable;

import br.com.elementi.core.tools.Regex;
import org.hibernate.Query;

/**
 * Created by eltonsolid on 27/12/16.
 */
public class QOperation implements QConstraint {

    private QOperator operator;
    private String alias;
    private String field;
    private Object value;

    public QOperation(QOperator operator, String alias, String field, Object value) {
        this.operator = operator;
        this.alias = alias;
        this.field = field;
        this.value = value;
    }

    public void joinQuery(StringBuilder query) {
        query.append(alias + "." + field + operator.operator() + parameterName());
    }


    @Override
    public void joinValue(Query query) {
        query.setParameter(parameterName(), value);
    }

    private String parameterName() {
        return ":" + Regex.replaceDotForUnderscor(field) + "$" + value.hashCode();
    }



}
