package br.com.elementi.core.schema;

import java.util.Iterator;
import java.util.LinkedList;

import com.google.common.collect.Lists;

public class DataSet implements Iterable<Row> {

	private String name;
	private LinkedList<Row> rows;

	private DataSet(String name) {
		this.rows = Lists.newLinkedList();
		this.name = name;
	}

	public static DataSet create(String name) {
		return new DataSet(name);
	}

	public void add(Row row) {
		this.rows.add(row);
	}

	public String getName() {
		return name;
	}

	@Override
	public Iterator<Row> iterator() {
		return rows.iterator();
	}

}
