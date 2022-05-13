package br.com.elementi.core.qtable;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * Created by eltonsolid on 26/12/16.
 */
public class QFrom2 {

    private LinkedList<Class<?>> classes;

    public QFrom2() {
        this.classes = Lists.newLinkedList();
    }

    public static QFrom2 create(Class<?> from) {
        return new QFrom2().add(from);
    }

    public String currentAlias() {

        return classes.size() == 1 ? "alias" : "alias" + (classes.size() - 1);
    }

    private QFrom2 add(Class<?> from) {
        classes.add(from);
        return this;
    }

    public void joinQuery(StringBuilder query) {
        query.append("FROM ");
        for (Class<?> from : classes) {
            int index = classes.indexOf(from);
            query.append(from.getSimpleName() + (index == 0 ? " alias" : " alias" + index));
        }
    }
}
