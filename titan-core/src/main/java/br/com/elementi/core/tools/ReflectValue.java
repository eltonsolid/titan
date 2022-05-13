package br.com.elementi.core.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import br.com.elementi.core.annotation.ReflectIgnore;

public class ReflectValue {

	private Field field;
	private Object valueFrom;

	public ReflectValue(Field field, Object valueFrom) {
		this.field = field;
		this.valueFrom = valueFrom;
	}

	public Field getField() {
		return field;
	}

	public Object getValueFrom() {
		return valueFrom;
	}

	public Boolean isNotEmpty() {
		return !(isEmpty());
	}

	public Boolean isEmpty() {
		return (valueFrom == null || valueFrom.toString().isEmpty());
	}

	public Boolean isPresent(Class<? extends Annotation> annotation) {
		return field.isAnnotationPresent(annotation);
	}

	public <T extends Annotation> T getValueOn(Class<T> annotation) {
		return (T) field.getAnnotation(annotation);
	}

	public boolean isNotIgnored() {
		return !(isPresent(ReflectIgnore.class));
	}

}
