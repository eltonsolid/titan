package br.com.elementi.core.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javassist.NotFoundException;
import br.com.elementi.core.model.ReflectDefaultValue;

public final class Init implements ReflectDefaultValue {

	private final Class<?> classe;

	private Init(Class<?> classe) {
		this.classe = classe;
	}

	@SuppressWarnings("unchecked")
	public static <T> T initializer(Class<T> classe) throws Exception {
		return (T) new Init(classe).initialized();
	}

	private Object initialized() throws Exception {
		Object instance = instance();
		field(instance);
		return instance;
	}

	private void field(Object instance) throws Exception {
		field(instance, classe.getDeclaredFields());
	}

	private Object instance() throws Exception {
		return Reflect.instance(classe);
	}

	private void field(Object instance, Field[] fields) throws Exception {
		for (Field field : fields) {
			if (!Modifier.isStatic(field.getModifiers())) {
				setFieldValue(instance, field);
			}
		}
	}

	private void setFieldValue(Object instance, Field field) throws Exception {
		field.setAccessible(true);
		field.set(instance, getDefaultValue(field.getType()));
	}

	private Object getDefaultValue(Class<?> type) throws Exception {
		return Reflect.defaultValue(this, type);
	}

	public Object reflectDefaultValue(Class<?> type) throws Exception {
		if (isFreeForInitializer(type)) {
			return initializer(type);
		}
		throw new NotFoundException("DefaultValue on Init for type :: " + type.getSimpleName());
	}

	private boolean isFreeForInitializer(Class<?> type) {
		return !type.equals(classe);
	}

}
