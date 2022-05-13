package br.com.elementi.core;

import org.junit.Test;

public class IdeiasTest {

	@Test
	public void testChoise() throws Exception {
		"constraint subApp_pk primary key(subApp_id)".replaceFirst("constraint [a-zA-Z_]*", "");
	}

	@Test
	public void testCriarumaformadealocaropcoes() throws Exception {
		// Criar umaforma de alocar opções do tipo sim e nao, mas como no
		// SINCAD, devemos ter mais uma opções para nao. Alguma coisa que possa
		// gerar um dominio sem
		// Criar uma nova tabela no banco de dados
		// Person.create(1).name("").register("").get();
		// Q().name("Eltonsolid").register("registros").list();

	}

}
