package br.com.elementi.core.mapper;

import java.util.List;
import java.util.Map.Entry;

import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.tools.Reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class Mapper {

	private Multimap<Class<?>, MapperTemplate> templates;

	private Mapper() {
		templates = HashMultimap.create();
	}

	public static Mapper start() {
		return new Mapper();
	}

	public void add(MapperTemplate template) throws Exception {
		templates.put(template.getFrom(), template);
	}

	@SuppressWarnings("unchecked")
	public <T> T mapping(Object from, Class<?> to) throws Exception {
		MapperTemplate template = find(from, to);
		if (template.isEmpty()) {
			throw new NotFoundException("Template not exist or is empty for Class<T> to :: " + to);
		}
		return (T) mapping(from, template);
	}

	private Object mapping(Object from, MapperTemplate template) throws Exception {
		return Reflect.isIterable(from) ? mappingMany((Iterable<?>) from, template) : mappingOne(from, template);
	}

	private Object mappingMany(Iterable<?> iterable, MapperTemplate template) throws Exception {
		List<Object> objects = Lists.newArrayList();
		for (Object from : iterable) {
			objects.add(mappingOne(from, template));
		}
		return objects;
	}

	private Object mappingOne(Object from, MapperTemplate template) throws Exception {
		Object to = template.instanceForTo();
		for (Entry<MapperField, MapperField> field : template) {
			resolver(from, field.getKey(), to, field.getValue());
		}
		return to;
	}

	private void resolver(Object from, MapperField fromField, Object to, MapperField toField) throws Exception {
		Object valueFrom = Reflect.getValueFrom(from, fromField);
		valueFrom = mappingIfExistTemplate(valueFrom, to, toField);
		Reflect.setValueTo(to, toField, valueFrom);
	}

	private Object mappingIfExistTemplate(Object from, Object to, MapperField toField) throws Exception {
		Class<?> classTo = Reflect.findDestinationClass(to.getClass(), toField);
		try {
			return mapping(from, classTo);
		} catch (NotFoundException exception) {
			return from;
		}
	}

	private MapperTemplate find(Object from, Class<?> to) throws Exception {
		for (MapperTemplate template : templates.get(Reflect.getClassForIterablesOrObject(from))) {
			if (template.isAvaliable(to)) {
				return template;
			}
		}
		return MapperTemplate.empty();
	}

	@VisibleForTesting
	public List<MapperTemplate> listFor(Class<?> from) throws Exception {
		return Lists.newArrayList(templates.get(from));
	}

}
