package br.com.elementi.core;

import br.com.elementi.core.schema.Database;
import br.com.elementi.core.schema.Table;

public class DatabaseCreater {

	public static void main(String[] args) throws Exception {
		Table application = Table.of("APPLICATION");
		application.primaryKey(6);
		application.varchar("name", 200);
		application.varchar("description", 200);
		application.timestamp("created");
		Table table = Table.of("DOCUMENT");
		table.primaryKey(6);
		table.varchar("code", 200);
		table.varchar("description", 200);
		table.foreingKey(application);
		Database.developement().create(table);
	}

}
