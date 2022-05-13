package br.com.elementi.core.qtable;

import br.com.elementi.core.model.Identity;
import com.google.common.collect.Lists;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by eltonsolid on 17/12/16.
 */
public class QTable2 implements QTableResult {

    private QSelect projection;
    private QFrom2 from;
    private LinkedList<QConstraint> constraints;
    private Session session;

    public QTable2() {
        this.projection = new QSelect();
        this.constraints = Lists.newLinkedList();
    }

    public QTable2(Class<?> from, Session session) {
        this();
        this.from = QFrom2.create(from);
        this.session = session;
    }

    public static QTable2 of(Class<?> from, Session session) {
        return new QTable2(from, session);

    }


    public QTable2 or() {
        constraints.add(QOr.POINT);
        return this;
    }


    public QTable2 eq(String field, Object value) {
        add(new QEqual2(from.currentAlias(), field, value));
        return this;

    }

    private void add(QConstraint constraint) {
        constraint = constraints.isEmpty() ? new QWhere(constraint) : constraints.remove(QOr.POINT) ? new QOr(constraint) : new QAnd(constraint);
        constraints.add(constraint);
    }


    @Override
    public String query() throws Exception {
        StringBuilder query = new StringBuilder();
        projection.joinQuery(query);
        from.joinQuery(query);
        constraints.forEach(constraint -> constraint.joinQuery(query));
        return query.toString();
    }

    @Override
    public <T> List<T> list() throws Exception {
        return null;
    }

    @Override
    public <T> T get() throws Exception {
        return null;
    }

    @Override
    public <T> T find(Identity identity) throws Exception {
        return null;
    }

    @Override
    public <T> LinkedList<T> link() throws Exception {
        return null;
    }

    @Override
    public <T> Set<T> set() throws Exception {
        return null;
    }

    @Override
    public <T extends Comparable<?>> SortedSet<T> sortedSet() throws Exception {
        return null;
    }

    public QTable2 distinct() {
        projection.distinct(from.currentAlias());
        return this;
    }

    public QTable2 count() {
        projection.count(from.currentAlias());
        return this;
    }

    public QTable2 select(String... fields) {
        for (String field : fields) {
            projection.project(from.currentAlias(), field);
        }
        return this;
    }
}
