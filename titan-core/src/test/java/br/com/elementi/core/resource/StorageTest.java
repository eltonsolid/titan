package br.com.elementi.core.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.Entity;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.qtable.QSearch;
import br.com.elementi.core.resource.Resources;
import br.com.elementi.core.resource.Storage;

public class StorageTest {

	private String path = "br.com.elementi.test.DummyEntity.class";
	private Storage storage;

	@Before
	public void before() throws Exception {
		storage = Storage.store(Resources.searchWith(path));
	}

	@Test
	public void testForAnnotation() throws Exception {
		List<Class<?>> classes = storage.listWithAnnotation(Entity.class);
		Class<?> classe = classes.get(0);
		assertEquals("br.com.elementi.test.DummyEntity", classe.getName());
	}

	@Test
	public void testForInterface() throws Exception {
		List<Class<QSearch>> classes = storage.listWithInterface(QSearch.class);
		Class<QSearch> classe = classes.get(0);
		assertEquals("br.com.elementi.test.DummyEntity", classe.getName());
	}
}
