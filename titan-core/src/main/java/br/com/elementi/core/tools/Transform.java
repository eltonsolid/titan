package br.com.elementi.core.tools;

import static br.com.elementi.core.constraint.Constraints.notEmpty;

public class Transform {

	public static String capitalize(String value) throws Exception {
		notEmpty(value);
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	public static String normalize(String value) throws Exception {
		notEmpty(value);
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}

}
