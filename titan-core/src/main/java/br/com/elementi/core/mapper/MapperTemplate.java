package br.com.elementi.core.mapper;

import static br.com.elementi.core.constraint.Constraints.notNull;

import java.util.Iterator;
import java.util.Map.Entry;

import br.com.elementi.core.tools.Reflect;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class MapperTemplate implements Iterable<Entry<MapperField, MapperField>> {

	private Class<?> from;
	private Class<?> to;
	private Multimap<MapperField, MapperField> values;

	private MapperTemplate(Class<?> from, Class<?> to, Multimap<MapperField, MapperField> values) {
		this.from = from;
		this.to = to;
		this.values = values;
	}

	public static MapperTemplate empty() {
		return new MapperTemplate(Object.class, Object.class, HashMultimap.<MapperField, MapperField> create());
	}

	public static MapperTemplate with(Class<?> from, Class<?> to) {
		return new MapperTemplate(from, to, HashMultimap.<MapperField, MapperField> create());
	}

	public static MapperTemplate with(Class<?> from, Class<?> to, MapperTemplate template) {
		return new MapperTemplate(from, to, template.values);
	}

	public Class<?> getFrom() {
		return from;
	}

	public Class<?> getTo() {
		return to;
	}

	public Object instanceForTo() throws Exception {
		return Reflect.instance(to);
	}

	public Boolean isAvaliable(Class<?> that) {
		return this.to.equals(that);
	}

	public Boolean isEmpty() {
		return values.isEmpty();
	}

	public MapperTemplate add(MapperField fromField, MapperField toField) throws Exception {
		values.put(notNull(fromField), notNull(toField));
		return this;
	}

	public MapperTemplate add(String fromField, String toField) throws Exception {
		add(MapperField.onGet(fromField), MapperField.onGet(toField));
		return this;
	}

	public Object resolver(Object from) throws Exception {
		Object instance = Reflect.instance(getTo());
		return resolver(from, instance);
	}

	private Object resolver(Object from, Object to) throws Exception {
		for (Entry<MapperField, MapperField> entry : values.entries()) {
			Reflect.resolver(from, entry.getKey(), to, entry.getValue());
		}
		return to;
	}

	public Integer size() {
		return values.size();
	}

	public Iterator<Entry<MapperField, MapperField>> iterator() {
		return values.entries().iterator();
	}

	@Override
	public boolean equals(Object that) {
		if (that instanceof MapperTemplate) {
			return equals(this.hashCode(), ((MapperTemplate) that).hashCode());
		}
		return false;
	}

	private boolean equals(Integer This, Integer That) {
		return This.equals(That);
	}

	@Override
	public int hashCode() {
		return from.hashCode() * 31 + to.hashCode();
	}

}
