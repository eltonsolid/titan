package br.com.elementi.core.xml;

import java.util.List;

import com.google.common.collect.Lists;

public class XmlException extends Exception {

	public class Value {
		String code;
		String description;
	}

	protected List<Value> values;

	public XmlException() {
		values = Lists.newArrayList();
	}

	public XmlException(String code, String description) {
		this();
		add(code, description);
	}

	public void add(String code, String description) {
		Value value = new Value();
		value.code = code;
		value.description = description;
		this.values.add(value);
	}

}
