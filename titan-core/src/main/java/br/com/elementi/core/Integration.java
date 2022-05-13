package br.com.elementi.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elementi.core.boot.Microcontainer;
import br.com.elementi.core.schema.Database;
import br.com.elementi.core.schema.Schema;
import br.com.elementi.core.tools.Reflect;

public abstract class Integration<SERVICE> extends Unit {

	private Schema schema;
	private AnnotationConfigApplicationContext context;

	public abstract void schemas(Schema schema) throws Exception;

	public final void beforeIntegration() throws Exception {
		schema = Schema.create(Database.developement());
		context = Microcontainer.forDeveloper();
		schemas(schema);
		schema.start();
	}

	public void afterIntegration() throws Exception {
		schema.finish();
	}

	@SuppressWarnings("unchecked")
	public SERVICE service() {
		return (SERVICE) context.getBean(Reflect.getClassDeclarated(getClass()));
	}

}
