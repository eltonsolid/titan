package br.com.elementi.core.xml;

import java.lang.reflect.Field;
import java.util.List;

import br.com.elementi.core.xml.XmlTemplate.XmlConverter;

public class XmlMapper {

	public static <T> T mapper(Object from, T to, List<XmlTemplate> templates) {
		return new XmlMapper().start(from, to, templates);
	}

	private <T> T start(Object from, T to, List<XmlTemplate> templates) {
		for (XmlTemplate template : templates) {
			Object found = get(from, template.from);
			XmlConverter converter = template.converter;
			Object converted = converter.apllay(template.from, found);
			set(converted, to, template.to);
		}
		return to;
	}

	private Object get(Object base, String path) {
		if (base == null) {
			return null;
		}
		if ("this".equals(path)) {
			return base;
		}
		Object from = base;
		for (String fieldName : path.split("\\.")) {
			from = get(from, fieldName, path);
			if (from == null) {
				return null;
			}
		}
		return from;
	}

	private Object get(Object from, String fieldName, String path) {
		try {
			for (Field field : from.getClass().getDeclaredFields()) {
				if (field.getName().equals(fieldName)) {
					field.setAccessible(true);
					return field.get(from);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new XmlValidateException("XmlNodeItem found invalid Field or Object on path:" + path);
		}
		return null;
	}

	private void set(Object converted, Object to, String fieldName) {
		try {
			for (Field field : to.getClass().getDeclaredFields()) {
				if (field.getName().equals(fieldName)) {
					field.setAccessible(true);
					field.set(to, converted);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new XmlValidateException("XmlNodeItem found invalid Field or Object on path:" + fieldName);
		}
	}

}
