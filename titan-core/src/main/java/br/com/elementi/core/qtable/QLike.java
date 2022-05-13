package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.LIKE;

public class QLike extends QAbstract {

    public enum QLikeType {ANYSIDE, START, END}


    public QLike(QLikeType type, String alias, String field, Object value) {
        super(alias, field, getSide(type, value), LIKE);
    }

    private static String getSide(QLikeType type, Object value) {
        switch (type) {
            case START:
                return value + "%";
            case END:
                return "%" + value;
            default:
                return "%" + value + "%";
        }

    }

}
