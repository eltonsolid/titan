package br.com.elementi.core.schema;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class Table {

	private String sequence;
	private String tableName;
	private LinkedList<Column> columns;
	private LinkedList<String> constraints;

	public Table() {
		this.sequence = "";
		this.columns = Lists.newLinkedList();
		this.constraints = Lists.newLinkedList();
	}

	public Table(String tableName) {
		this();

		this.tableName = tableName;
		this.sequence = "";
	}

	public class Value {
		Column column;
		Object value;

		public Value(Column column, Object value) {
			this.column = column;
			this.value = value;
		}

		public String converted() {
			return column.converter().value(value);
		}
	}

	public class Key {
		Value value;

		public Integer Get() {
			return (Integer) value.value;
		}

	}

	public Key key(Integer id) {
		Key key = new Key();
		key.value = new Value(primaryKey(), id);
		return key;
	}

	public static Table of(String tableName) {
		return new Table(tableName).primaryKey(6);
	}

	public void number(String fieldName, int value) {
		add(Column.number(fieldName, value));
	}

	public void varchar(String fieldName, int value) {
		add(Column.string(fieldName, value));
	}

	public void timestamp(String fieldName) {
		add(Column.timestamp(fieldName));
	}

	public void date(String fieldName) {
		add(Column.date(fieldName));
	}

	public String lasColumnName() {
		return columns.getLast().name();
	}

	public Integer columnSize() {
		return columns.size();
	}

	public Column column(int index) {
		return columns.get(index);
	}

	public Stream<Column> columns() {
		return columns.stream();
	}

	public String getTableName() {
		return tableName;
	}

	public Column primaryKey() {
		return columns.getFirst();
	}

	public String create() {
		StringBuilder header = new StringBuilder("CREATE TABLE " + tableName + "(");
		for (Column column : columns) {
			header.append(column.create() + ",");
		}
		for (String constraint : constraints) {
			header.append(constraint + ",");
		}
		return header.deleteCharAt(header.length() - 1).append(");").toString();
	}

	public List<String> drop() throws Exception {
		List<String> drops = Lists.newArrayList();
		if (!sequence.isEmpty()) {
			drops.add("DROP SEQUENCE SEQ_" + tableName);
		}
		drops.add("DROP TABLE " + tableName);
		return drops;
	}

	public Table primaryKey(int value) {
		number(tableName + "_id", value);
		constraint("constraint " + primaryKey().name().replace("id", "pk") + " primary key(" + primaryKey().name() + ")");
		return this;
	}

	public void foreingKey(Table foreign) {
		String foreignKey = this.tableName + "_" + foreign.tableName + "_fk FOREIGN KEY(" + foreign.primaryKey().name() + ")";
		String references = " REFERENCES " + foreign.tableName + "(" + foreign.primaryKey().name() + ")";
		String constraint = "constraint " + foreignKey + references;
		constraint(constraint);
		columns.add(foreign.primaryKey());
	}

	private void add(Column column) {
		columns.remove(column);
		columns.add(column);
	}

	private void constraint(String constraint) {
		constraints.remove(constraint);
		constraints.add(constraint);
	}

	public void withSequence() {
		this.sequence = "CREATE SEQUENCE SEQ_" + tableName + " START WITH 1 NOCACHE;";
	}

	public String sequence() {
		return this.sequence;
	}

	public Value columnValue(String columnName, Object value) {
		for (Column column : columns) {
			if (column.name().equalsIgnoreCase(columnName)) {
				return new Value(column, value);
			}
		}
		throw new IllegalArgumentException();
	}

	public String insert(Integer id, Object... objects) {
		return insert(key(id), objects);
	}

	public String insert(Key key, Object... objects) {
		List<Value> defaultValues = defaultValues(key);
		List<Value> newValues = newValues(objects);
		validate(newValues);
		merge(defaultValues, newValues);
		return query(defaultValues);
	}

	private void validate(List<Value> values) {
		for (Value value : values) {
			if (!columns.contains(value.column)) {
				throw new IllegalArgumentException();
			}
		}

	}

	public String query(List<Value> valueObjects) {
		StringBuilder names = new StringBuilder();
		StringBuilder values = new StringBuilder();
		String node = "";
		for (Value value : valueObjects) {
			names.append(node + value.column.name());
			values.append(node + value.converted());
			node = ", ";
		}
		return "INSERT INTO " + tableName + "(" + names + ")VALUES(" + values + ")";
	}

	public List<Value> defaultValues(Key key) {
		List<Value> values = Lists.newArrayList(key.value);
		columns.stream().skip(1).forEach(column -> values.add(new Value(column, column.defaultValue())));
		return values;
	}

	private List<Value> newValues(Object... objects) {
		List<Value> newValues = Lists.newArrayList();
		int index = 1;
		for (Object object : objects) {
			newValues.add(newValue(object, columns.get(index++)));
		}
		return newValues;
	}

	private Value newValue(Object object, Column column) {
		if (object instanceof Key) {
			return ((Key) object).value;
		}
		if (object instanceof Value) {
			return (Value) object;
		}
		return new Value(column, object);
	}

	public void merge(List<Value> defaultValues, List<Value> newValues) {
		for (Value newValue : newValues) {
			for (Value value : defaultValues) {
				if (value.column.name().equals(newValue.column.name())) {
					value.value = newValue.value;
				}
			}
		}
	}

	public String delete(Integer id) {
		return "DELETE " + tableName + " WHERE " + primaryKey().name() + " = " + id;
	}

	public String delete(Key key) {
		return "DELETE " + tableName + " WHERE " + primaryKey().name() + " = " + key.value;
	}

	public String deleteAll() {
		return "DELETE " + tableName;
	}

	public Row row(Integer id, Object... objects) {
		return row(key(id), objects);
	}

	public Row row(Key key, Object... objects) {
		return new Row() {

			@Override
			public String insert() {
				return Table.this.insert(key, objects);
			}

			@Override
			public String delete() {
				return Table.this.delete(key);
			}
		};
	}

	@Override
	public String toString() {
		return columns.toString();
	}
}
