package br.com.elementi.core.schema;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.schema.Table.Key;
import br.com.elementi.core.schema.Table.Value;

public class TableTest extends Unit {

	@Test
	public void testTableName() throws Exception {
		Table table = Table.of("APPLICATION");
		assertEquals("APPLICATION", table.getTableName());
	}

	@Test
	public void testImplicitPrimaryKey() throws Exception {
		Table table = Table.of("APPLICATION");
		assertEquals("APPLICATION_id", table.primaryKey().name());
	}

	@Test
	public void testNumberField() throws Exception {
		Table table = Table.of("APPLICATION");
		table.number("quantity", 6);
		assertEquals("quantity", table.lasColumnName());
	}

	@Test
	public void testVarcharField() throws Exception {
		Table table = Table.of("APPLICATION");
		table.varchar("name", 6);
		assertEquals("name", table.lasColumnName());
	}

	@Test
	public void testTimestampField() throws Exception {
		Table table = Table.of("APPLICATION");
		table.timestamp("create");
		assertEquals("create", table.lasColumnName());
	}

	@Test
	public void testDuplicateColumn() throws Exception {
		Table table = Table.of("APPLICATION");
		table.timestamp("create");
		table.timestamp("create");
		assertEquals(2, table.columnSize().intValue());
	}

	@Test
	public void testCreate() throws Exception {
		Table table = Table.of("application");
		String create = table.create();
		assertEquals(create, "CREATE TABLE application(application_id number(6),constraint application_pk primary key(application_id));");
	}

	@Test
	public void testDrop() throws Exception {
		Table table = Table.of("APPLICATION");
		List<String> drop = table.drop();
		assertEquals("DROP TABLE APPLICATION", drop.get(0));
	}

	@Test
	public void testCreateFullTable() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		String create = table.create();
		assertEquals(create, "CREATE TABLE application(application_id number(6),name varchar(200),description varchar(200),created timestamp,constraint application_pk primary key(application_id));");
	}

	@Test
	public void testForeningKey() throws Exception {
		Table table = Table.of("application");
		Table subTable = Table.of("subApp");
		subTable.foreingKey(table);
		assertEquals(subTable.create(),
				"CREATE TABLE subApp(subApp_id number(6),application_id number(6),constraint subApp_pk primary key(subApp_id),constraint subApp_application_fk FOREIGN KEY(application_id) REFERENCES application(application_id));");
	}

	@Test
	public void testSequence() throws Exception {
		Table table = Table.of("APPLICATION");
		table.withSequence();
		assertEquals(table.sequence(), "CREATE SEQUENCE SEQ_APPLICATION START WITH 1 NOCACHE;");
	}

	@Test
	public void testKey() throws Exception {
		Table table = Table.of("APPLICATION");
		table.key(1);
	}

	@Test
	public void testValue() throws Exception {
		Table table = Table.of("APPLICATION");
		table.varchar("NAME", 100);
		Value value = table.columnValue("name", "Eltonsolid");
		assertEquals("NAME", value.column.name());
		assertEquals("Eltonsolid", value.value);
	}

	@Test
	public void testValueGet() throws Exception {
		Table table = Table.of("APPLICATION");
		table.varchar("name", 100);
		table.number("age", 6);
		Value value = table.columnValue("age", 32);
		assertEquals("age", value.column.name());
		assertEquals(32, value.value);
	}

	@Test
	public void testInsert() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		String insert = table.insert(1);
		String expected = "INSERT INTO application(application_id, name, description, created)VALUES(1, 'name', 'description', " + DateTime.now().toString("yyyy-MM-dd hh:mm:ss") + ")";
		assertEquals(expected, insert);
	}

	@Test
	public void testInsertOnSetValue() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		Value value = table.columnValue("description", "Testing position Value");

		String insert = table.insert(1, value);
		assertEquals("INSERT INTO application(application_id, name, description, created)VALUES(1, 'name', 'Testing position Value', " + DateTime.now().toString("yyyy-MM-dd hh:mm:ss") + ")", insert);
	}

	@Test
	public void testInsertJoinValueDefault() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		DateTime now = DateTime.now().plusHours(1);
		String insert = table.insert(1,"Eltonsolid","Testing Insert", now);
		String expected = "INSERT INTO application(application_id, name, description, created)VALUES(1, 'Eltonsolid', 'Testing Insert', " + now.toString("yyyy-MM-dd hh:mm:ss") + ")";
		assertEquals(expected , insert);
	}

	@Test
	public void testInsertWithForeingKey() throws Exception {
		Table table = Table.of("application");
		Table subTable = Table.of("document");
		subTable.foreingKey(table);
		subTable.varchar("name", 100);
		Key application = table.key(1);
		String insert = subTable.insert(1, application);
		assertEquals("INSERT INTO document(document_id, application_id, name)VALUES(1, 1, 'name')", insert);
	}

	@Test
	public void testInsertWithForeingKeyOrder() throws Exception {
		Table table = Table.of("application");
		Table subTable = Table.of("document");
		subTable.varchar("name", 100);
		subTable.foreingKey(table);
		Key application = table.key(1);
		String insert = subTable.insert(1, application);
		assertEquals("INSERT INTO document(document_id, name, application_id)VALUES(1, 'name', 1)", insert);
	}

	@Test
	public void testInsertWithForeingKeyAndValue() throws Exception {
		Table application = Table.of("application");
		Key application_id = application.key(1);
		Table document = Table.of("document");
		document.foreingKey(application);
		document.varchar(NAME, 100);
		String insert = document.row(1, application_id, "Eltonsolid").insert();
		assertEquals("INSERT INTO document(document_id, application_id, NAME)VALUES(1, 1, 'Eltonsolid')", insert);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertInvalidValue() throws Exception {
		Table application = Table.of("application");
		Table document = Table.of("document");
		document.varchar(NAME, 100);
		document.insert(1, application.key(1));
	}

	@Test
	public void testDelete() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		String delete = table.delete(ONE);
		assertEquals("DELETE application WHERE application_id = 1", delete);
	}

	@Test
	public void testDeleteAll() throws Exception {
		Table table = Table.of("APPLICATION");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		String delete = table.deleteAll();
		assertEquals("DELETE APPLICATION", delete);
	}

	@Test
	public void testRowInsert() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		Row row = table.row(1);
		assertEquals(row.insert(), "INSERT INTO application(application_id, name, description)VALUES(1, 'name', 'description')");
	}

	@Test
	public void testRowDelete() throws Exception {
		Table table = Table.of("application");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		Row row = table.row(1);
		assertEquals("DELETE application WHERE application_id = 1", row.delete());
	}

}
