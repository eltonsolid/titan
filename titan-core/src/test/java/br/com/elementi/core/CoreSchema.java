package br.com.elementi.core;

import br.com.elementi.core.schema.Database;
import br.com.elementi.core.schema.Table;

public class CoreSchema {

	public static void main(String[] args) throws Exception {
		Database developement = Database.developement();
		developement.create(application());
		developement.create(document());
		developement.create(person());
	}

	public static Table application(){
		Table table = Table.of("APPLICATION");
		table.varchar("name", 200);
		table.varchar("description", 200);
		table.timestamp("created");
		return table;
	}

	public static Table document(){
		Table table = Table.of("document");
		table.foreingKey(application());
		table.varchar("code", 200);
		table.varchar("description", 200);
		return table;
	}

	public static Table person(){
		Table table = Table.of("person");
		table.foreingKey(document());
		table.foreingKey(application());
		table.varchar("name", 200);
		table.timestamp("created");
		return table;
	}
}
