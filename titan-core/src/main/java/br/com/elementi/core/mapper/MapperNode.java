package br.com.elementi.core.mapper;

import static br.com.elementi.core.constraint.Constraints.notEmpty;
import br.com.elementi.core.tools.Transform;

public class MapperNode {

	private String value;
	private Boolean fieldOnly;
	private String nameForGet;
	private String nameForSet;

	private MapperNode() {
		fieldOnly = false;
		nameForGet = "";
		nameForSet = "";
	}

	private MapperNode(String value) {
		this();
		this.value = value;
	}

	public static MapperNode onField2() {
		return new MapperNode("").usingField(Boolean.TRUE);
	}
	
	public static MapperNode onField(String value) {
		return new MapperNode(value).usingField(Boolean.TRUE);
	}
	
	public static MapperNode onGet(String value) {
		return new MapperNode(value).usingField(Boolean.FALSE);
	}

	public static MapperNode nameGet(String value, String forGet) throws Exception {
		return new MapperNode(value).forGet(forGet);
	}

	public static MapperNode nameSet(String value, String forSet) throws Exception {
		return new MapperNode(value).forSet(forSet);
	}

	private MapperNode usingField(Boolean forField) {
		this.fieldOnly = forField;
		return this;
	}

	private MapperNode forGet(String forGet) throws Exception {
		nameForGet = notEmpty(forGet);
		return this;
	}

	private MapperNode forSet(String forSet) throws Exception {
		nameForSet = notEmpty(forSet);
		return this;
	}

	public String name() {
		return value;
	}

	private String valueForBean() throws Exception {
		return Transform.capitalize(value);
	}

	public String nameForGet() throws Exception {
		if (nameForGet.isEmpty()) {
			return "get" + valueForBean();
		}
		return nameForGet;
	}

	public String nameForSet() throws Exception {
		if (nameForSet.isEmpty()) {
			return "set" + valueForBean();
		}
		return nameForSet;
	}

	public boolean isFieldOnly() {
		return fieldOnly;
	}

	@Override
	public String toString() {
		return value;
	}
}
