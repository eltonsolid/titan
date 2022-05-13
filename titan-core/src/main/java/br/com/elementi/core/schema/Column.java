package br.com.elementi.core.schema;

import org.joda.time.DateTime;

public class Column {

	private String name;
	private String define;
	private Object defaultValue;
	private Converter converter;

	private static Converter NUMBER = value -> Integer.parseInt(value + "") + "";
	private static Converter STRING = value -> "'" + value + "'";
	private static Converter DATE = value -> ((DateTime) value).toString("yyyy-MM-dd");
	private static Converter TIMESTAMP = value -> ((DateTime) value).toString("yyyy-MM-dd hh:mm:ss");

	public interface Converter {
		public String value(Object object);
	}

	public Column(String name, String define, Object valueDefault, Converter converter) {
		super();
		this.name = name;
		this.define = define;
		this.defaultValue = valueDefault;
		this.converter = converter;
	}

	public static Column number(String name, int define) {
		return new Column(name, "number(" + define + ")", "-1", NUMBER);
	}

	public static Column string(String name, int define) {
		return new Column(name, "varchar(" + define + ")", name, STRING);
	}

	public static Column date(String name) {
		return new Column(name, "date", DateTime.now(), DATE);
	}

	public static Column timestamp(String name) {
		return new Column(name, "timestamp", DateTime.now(), TIMESTAMP);
	}

	public String create() {
		return name + " " + define;
	}

	public String name() {
		return name;
	}

	public String define() {
		return define;
	}

	public Object defaultValue() {
		return defaultValue;
	}

	public Converter converter() {
		return converter;
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof Column ? ((Column) object).name.equals(this.name) : false;
	}

}
