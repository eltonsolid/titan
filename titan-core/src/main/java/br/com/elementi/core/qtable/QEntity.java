package br.com.elementi.core.qtable;

import br.com.elementi.core.annotation.QSearchLike;
import br.com.elementi.core.domain.DomainEntity;
import br.com.elementi.core.exception.NotExpectedException;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.tools.Init;
import br.com.elementi.core.tools.Reflect;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by eltonsolid on 20/12/16.
 * <p>
 * Criado sem rela��o direta com o EntityDomanin, para que possamos criar uma classe de base que use valores default, Como nome, usuario ou data de cria��o.
 * Em um dominio complexo, temos campos de mesmo nome ou de mesmo proposito. Podendo ser um campo de auditoria ou de pesquisa.
 * Isso vai permitir que seja criado um template de cria��o de query, limitando a duplicidade de campos criados.
 * A classe Tableresult permitiu que os campos de Entidade e de Table possam ser referenciados separadamente.
 * Podemos ainda extender o template utlizando apenas o campos que precisamos, dando uma maior liberdade
 */
public interface QEntity<Q> extends QTableResult {

    Q id(Identity id);

    Q or();

    static <Q extends QEntity, E extends DomainEntity> Q of(Class<Q> qEntity, Class<E> entityClass) throws Exception {
        E entity = Init.initializer(entityClass);
        QTableResult result = implement(entity);
        MethodHandler handler = createHandlerEntity(qEntity, entity, result);
        return Reflect.proxyInstance(qEntity, handler);
    }

    static <Q extends QEntity> Q of(Class<Q> qEntity, QTable table) throws Exception {
        MethodHandler handler = createHandlerQTable(table);
        return Reflect.proxyInstance(qEntity, handler);
    }

    static MethodHandler createHandlerQTable(QTable table) {
        return (self, thisMethod, proceed, args) -> {
            if (qTableResultHas(thisMethod)) {
                return thisMethod.invoke(table);
            }
            if (isOrInvoked(thisMethod))
                table.or();
            else if (thisMethod.getDeclaredAnnotation(QSearchLike.class) != null)
                table.lk(thisMethod.getName(), (String) args[0]);
            else
                table.eq(thisMethod.getName(), args[0]);
            return self;
        };
    }

    static <Q extends QEntity, E extends DomainEntity> MethodHandler createHandlerEntity(Class<Q> qEntity, E entity, QTableResult result) {
        return (self, thisMethod, proceed, args) -> {
            if (qTableResultHas(thisMethod)) {
                return thisMethod.invoke(result);
            } else {
                validateTypeReturn(qEntity, thisMethod.getReturnType());
                Reflect.setValueTo(entity, thisMethod.getName(), args[0]);
                return self;
            }
        };
    }


    static boolean isOrInvoked(Method thisMethod) {
        return thisMethod.getName().equals("or");
    }

    static boolean qTableResultHas(Method thisMethod) {
        return QTableResult.class.equals(thisMethod.getDeclaringClass());
    }


    static <E extends DomainEntity> QTableResult implement(E entity) {
        return new QTableResult() {

            @Override
            public String query() throws Exception {
                return "";
            }

            @Override
            public E find(Identity identity) throws Exception {
                return entity;
            }

            @Override
            public E get() throws Exception {
                return entity;
            }

            @Override
            public List<E> list() throws Exception {
                return Lists.newArrayList(entity);
            }

            @Override
            public LinkedList<E> link() throws Exception {
                return Lists.newLinkedList(list());
            }

            @Override
            public Set<E> set() throws Exception {
                return Sets.newHashSet(entity);
            }

            @Override
            public <E extends Comparable<?>> SortedSet<E> sortedSet() throws Exception {
                return Sets.newTreeSet((Iterable<? extends E>) list());
            }
        };
    }


    static void validateTypeReturn(Class<?> builder, Class<?> returnType) throws Exception {
        if (!returnType.isAssignableFrom(builder)) {
            throw new NotExpectedException("QEntity Expected type :: " + builder.getSimpleName() + " but found ::" + returnType.getSimpleName());
        }
    }
}
