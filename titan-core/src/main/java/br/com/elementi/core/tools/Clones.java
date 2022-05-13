package br.com.elementi.core.tools;

import java.lang.reflect.Field;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Clones {

	@SuppressWarnings("unchecked")
	public static <T> T clone(T original) throws Exception {
		T clone = (T) Reflect.instance(original.getClass());
		clone(original, clone);
		return clone;
	}

	public static Object clone(Object original, Object clone) throws Exception {
		Iterable<Field> fields = concatFields(original);
		for (Field field : fields) {
			clone(original, clone, field);
		}
		return clone;
	}

	private static void clone(Object original, Object clone, Field field) throws IllegalAccessException, Exception {
		field.setAccessible(true);
		Object object = field.get(original);
		if (isToCloneAgain(object)) {
			object = clone(object);
		}
		field.set(clone, object);
	}

	private static boolean isToCloneAgain(Object object) {
		return object != null && object.getClass().getSuperclass().isAssignableFrom(Object.class);
	}

	private static Iterable<Field> concatFields(Object original) {
		List<Field> fieldsClass = Lists.newArrayList(original.getClass().getDeclaredFields());
		List<Field> fieldsSuperClass = Lists.newArrayList(original.getClass().getSuperclass().getDeclaredFields());
		Iterable<Field> fields = Iterables.concat(fieldsClass, fieldsSuperClass);
		return fields;
	}
}
