package br.com.elementi.core.qtable;

import org.hibernate.Query;

/**
 * Created by eltonsolid on 26/12/16.
 */
public class QEqual2 extends QOperation {

    public QEqual2(String alias, String field, Object value) {
        super(QOperator.EQUAL, alias, field, value);
    }


}
