package br.com.elementi.core.qtable;

import br.com.elementi.core.model.Identity;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by eltonsolid on 20/12/16.
 */
public interface QTableResult {

    String query() throws Exception;

    <T> T find(Identity identity) throws Exception;

    <T> T get() throws Exception;

    <T> List<T> list() throws Exception;

    <T> LinkedList<T> link() throws Exception;

    <T> Set<T> set() throws Exception;

    <T extends Comparable<?>> SortedSet<T> sortedSet() throws Exception;

}
