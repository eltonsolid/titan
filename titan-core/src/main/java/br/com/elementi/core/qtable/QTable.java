package br.com.elementi.core.qtable;

import br.com.elementi.core.annotation.QSearchEqual;
import br.com.elementi.core.annotation.QSearchLike;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.tools.Reflect;
import br.com.elementi.core.tools.Reflect.ReflectPath;
import br.com.elementi.core.tools.ReflectValue;
import com.google.common.collect.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

public class QTable implements QTableResult {

    private QSelect projection;
    private QFrom from;
    private LinkedList<QQuery> fetch;
    private LinkedList<QConstraint> constraints;
    private Session session;

    private QTable() {
        this.projection = new QSelect();
        this.fetch = Lists.newLinkedList();
        this.constraints = Lists.newLinkedList();
    }

    public QTable(Class<?> from, Session session) throws Exception {
        this();
        this.from = new QFrom(from);
        this.session = session;
    }

    public static QTable of(Class<?> from, Session session) throws Exception {
        return new QTable(from, session);
    }

    public static QTable of(QSearch search, Session session) throws Exception {
        return of(search.from(), session).search(search);
    }

    private QTable search(QSearch search) throws Exception {
        for (ReflectValue reflectField : Reflect.allFieldValuesOnBean(search)) {
            if (reflectField.isNotEmpty()) {
                search(reflectField);
            }
        }
        return this;
    }

    private void search(ReflectValue reflectField) throws Exception {
        if (reflectField.isPresent(QSearchEqual.class)) {
            eq(reflectField.getValueOn(QSearchEqual.class).value(), reflectField.getValueFrom());
        }
        if (reflectField.isPresent(QSearchLike.class)) {
            lk(reflectField.getValueOn(QSearchLike.class).value(), reflectField.getValueFrom().toString());
        }
    }

    public QFrom from() {
        return from;
    }

    public QTable eq(String fieldName, Object value) {
        add(new QEqual(from.currentAlias(), fieldName, value));
        return this;
    }

    public QTable lk(String fieldName, String value) {
        add(new QLike(QLike.QLikeType.ANYSIDE, from.currentAlias(), fieldName, value));
        return this;
    }

    public QTable lkStart(String fieldName, String value) {
        add(new QLike(QLike.QLikeType.START, from.currentAlias(), fieldName, value));
        return this;
    }

    public QTable lkEnd(String fieldName, String value) {
        add(new QLike(QLike.QLikeType.END, from.currentAlias(), fieldName, value));
        return this;
    }


    public QTable between(String field, Integer first, Integer second) {
        add(new QBetween(from.currentAlias(), field, first, second));
        return this;
    }

    public QTable gt(String field, Integer value) {
        add(new QGt(from.currentAlias(), field, value));
        return this;
    }

    public QTable lt(String field, Integer value) {
        add(new QLt(from.currentAlias(), field, value));
        return this;
    }

    public QTable in(String field, Object... value) {
        add(new QIn(from.currentAlias(), field, value));
        return this;
    }

    public QTable lenth(String field, Integer value) {
        return this;
    }

    public QTable join(Class<?> with) throws Exception {
        ReflectPath found = Reflect.findBound(from.entityClass(), with);
        join(with, found.fromName(), found.toName());
        return this;
    }

    public void join(Class<?> with, String fromName, String toName) throws Exception {
        from.join(with);
        add(new QOn(from.baseAlias(), fromName, from.currentAlias(), toName));
    }

    public QTable distinct() {
        projection.distinct(from.currentAlias());
        return this;
    }

    public QTable count() {
        projection.count(from.currentAlias());
        return this;
    }

    public QTable select(String... fields) {
        for (String field : fields) {
            projection.project(from.currentAlias(), field);
        }
        return this;
    }

    public QTable fetch(String field) {
        distinct();
        fetch.add(new QFetch(from.currentAlias(), field));
        return this;
    }

    public QTable or() {
        constraints.add(QOr.POINT);
        return this;
    }

    private void add(QConstraint constraint) {
        constraint = constraints.isEmpty() ? new QWhere(constraint) : constraints.remove(QOr.POINT) ? new QOr(constraint) : new QAnd(constraint);
        constraints.add(constraint);
    }

    public String query() throws Exception {
        StringBuilder query = new StringBuilder();
        all().forEach(q -> q.joinQuery(query));
        System.out.println("::::: ::::: ::::: ::::: ::::: {" + query + "}");
        return query.toString();
    }

    private List<QQuery> all() {
        List<QQuery> all = new ArrayList<>();
        all.add(projection);
        all.add(from);
        all.addAll(fetch);
        all.addAll(constraints);
        return all;
    }

    @SuppressWarnings("unchecked")
    private <T> Iterable<T> iterable() throws Exception {
        Query query = session.createQuery(query());
        constraints.forEach(q -> q.joinValue(query));
        return query.list();
    }

    @Override
    public <T> T get() throws Exception {
        return find();
    }

    public <T> T find() throws Exception {
        Iterable<T> iterable = iterable();
        int size = Iterables.size(iterable);
        if (size == 1) {
            return iterable.iterator().next();
        }
        throw (size == 0) ? from().notFoundException() : from().notUniqueFoundException();
    }

    public Boolean exist() throws Exception {
        Iterable<?> iterable = iterable();
        return iterable.iterator().hasNext() ? true : false;
    }

    public <T> T find(Identity identity) throws Exception {
        return this.eq("id", identity).find();
    }

    public <T> QOptional findOptional() throws Exception {
        Iterator<Object> iterator = iterable().iterator();
        Object found = iterator.hasNext() ? iterator.next() : null;
        return QOptional.of(from.entityClass(), found);
    }

    public <T> List<T> list() throws Exception {
        Iterable<T> iterable = iterable();
        return Lists.newArrayList(iterable);
    }

    public <T> LinkedList<T> link() throws Exception {
        Iterable<T> iterable = iterable();
        return Lists.newLinkedList(iterable);
    }

    public <T> Set<T> set() throws Exception {
        Iterable<T> iterable = iterable();
        return Sets.newHashSet(iterable);
    }

    public <T extends Comparable<?>> SortedSet<T> sortedSet() throws Exception {
        Iterable<T> iterable = iterable();
        return Sets.newTreeSet(iterable);
    }

    @SuppressWarnings("unchecked")
    public <KEY, T> Map<KEY, T> map(String field) throws Exception {
        HashMap<KEY, T> map = Maps.newHashMap();
        for (T t : this.<T>iterable()) {
            KEY key = (KEY) Reflect.getValueFrom(t, field);
            map.put(key, t);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public <KEY, T> Multimap<KEY, T> multMap(String field) throws Exception {
        Multimap<KEY, T> multimap = ArrayListMultimap.create();
        for (T t : this.<T>iterable()) {
            KEY key = (KEY) Reflect.getValueFrom(t, field);
            multimap.put(key, t);
        }
        return multimap;
    }

    public QTableResult max(int max) {

        return this;
    }
}
