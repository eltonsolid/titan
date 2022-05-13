package br.com.elementi.core.schema;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;

public class DatabaseTest extends Unit {

	private Database database;
	private Table tables;
	private Table tablesIn;

	@Before
	public void before() throws Exception {
		tables = Table.of("TEST");
		tables.primaryKey(6);
		tables.withSequence();
		tables.varchar("description", 100);
		tablesIn = Table.of("INTEST");
		tablesIn.primaryKey(6);
		tablesIn.varchar("description", 100);
		database = Database.developement();
		after();
		database.create(tables);
		database.create(tablesIn);
	}

	@Test
	public void testExecute() throws Exception {
		database.insertUpdate("INSERT INTO TEST VALUES(1,'TEST')");
	}

	@Test
	public void testExecuteSecondTable() throws Exception {
		database.insertUpdate("INSERT INTO INTEST VALUES(1,'TEST')");
	}

	@Test
	public void testSelect() throws Exception {
		database.insertUpdate("INSERT INTO TEST VALUES(1,'TEST')");
		ResultSet resultSet = database.select("SELECT * FROM TEST");
		resultSet.next();
		Integer id = resultSet.getInt("test_id");
		assertEquals(ONE, id);
	}

	@After
	public void after() throws Exception {
		database.drop(tables);
		database.drop(tablesIn);
	}

}
