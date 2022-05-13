package br.com.elementi.core.check;

import java.util.LinkedList;

import br.com.elementi.core.exception.CheckException;
import br.com.elementi.core.tools.Reflect;
import br.com.elementi.core.tools.ReflectValue;

import com.google.common.collect.Lists;

public class Check {

	private LinkedList<String> checkeds;

	public Check(LinkedList<String> checkeds) {
		this.checkeds = checkeds;
	}

	public static Check from() {
		return new Check(Lists.<String> newLinkedList());
	}

	public Check check(String key, String value) throws Exception {
		if (value == null || value.isEmpty()) {
			checkeds.add(key);
		}
		return this;
	}

	public <N extends Number> Check check(String key, N number) {
		if (number == null) {
			checkeds.add(key);
		}
		return this;
	}

	public Check check(Object object) throws Exception {
		for (ReflectValue field : Reflect.allFieldValuesOnBean(object)) {
			if (field.isEmpty() && field.isNotIgnored()) {
				checkeds.add(field.getField().getName());
			}
		}
		return this;
	}

	public void validate() throws Exception {
		if (!checkeds.isEmpty()) {
			throw new CheckException(checkeds);
		}
	}

	public Boolean isValid() {
		return checkeds.isEmpty();
	}

}
