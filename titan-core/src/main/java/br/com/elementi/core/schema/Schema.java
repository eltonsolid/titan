package br.com.elementi.core.schema;

import static br.com.elementi.core.constraint.Constraints.notNull;

import java.util.LinkedHashSet;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class Schema {

	private Database database;
	private LinkedHashSet<Row> rows;
	private LinkedHashSet<Delete> cleaner;

	private Schema() {
		rows = Sets.newLinkedHashSet();
		cleaner = Sets.newLinkedHashSet();
	}

	private Schema(Database database) {
		this();
		this.database = database;
	}

	public static Schema create(Database database) throws Exception {
		notNull(database);
		return new Schema(database);
	}

	public void add(DataSet dataSet) {
		for (Row row : dataSet) {
			rows.add(row);
		}
	}

	public void add(Row row) {
		rows.add(row);
	}

	public void clear(Delete clear) {
		cleaner.add(clear);
	}

	public void start() throws Exception {
		finish();
		insert();
	}

	public void finish() throws Exception {
		cleaner();
		delete();
	}

	private void cleaner() throws Exception {
		List<Delete> deletes = Lists.newArrayList(cleaner);
		for (Delete delete : Lists.reverse(deletes)) {
			database(delete.delete());
		}
	}

	private void insert() throws Exception {
		for (Row row : rows) {
			database(row.insert());
		}
	}

	private void delete() throws Exception {
		List<Row> deletes = Lists.newArrayList(rows);
		for (Row row : Lists.reverse(deletes)) {
			database(row.delete());
		}
	}

	private void database(String query) throws Exception {
		database.insertUpdate(query);
	}

	public Integer size() {
		return rows.size();
	}

}
