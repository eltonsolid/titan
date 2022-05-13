package br.com.elementi.core.schema;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import com.google.common.annotations.VisibleForTesting;

public class Database {

	private DataSource dataSource;

	private Database() throws Exception {
	}

	private Database(DataSource dataSource) throws Exception {
		this();
		this.dataSource = dataSource;
	}

	public static Database developement() throws Exception {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:./src/test/resources/database/test");
		dataSource.setUser("");
		dataSource.setPassword("");
		return new Database(dataSource);
	}

	private Connection connection() throws Exception {
		return dataSource.getConnection();
	}

	public void create(Table table) throws Exception {
		connection().createStatement().executeUpdate(table.sequence());
		connection().createStatement().executeUpdate(table.create());
	}

	public void drop(Table script) throws Exception {
		for (String drop : script.drop()) {
			try {
				connection().createStatement().executeUpdate(drop);
			} catch (Exception exception) {
				System.err.println("DATABASE DESTROIER ERROR :: " + exception.getMessage());
				System.err.println(script.drop());
			}
		}
	}

	public void insertUpdate(String query) throws Exception {
		connection().createStatement().executeUpdate(query);
	}

	@VisibleForTesting
	public ResultSet select(String query) throws Exception, Exception {
		return connection().createStatement().executeQuery(query);
	}

}
