package br.com.elementi.core.tools;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.test.DummyEnum;
import br.com.elementi.test.DummyException;
import br.com.elementi.test.DummyPath;
import br.com.elementi.test.test.DummySubPath;

import com.google.common.collect.Lists;

public class I18nTest {

	private I18n i18n;

	@Before
	public void before() throws Exception {
		i18n = I18n.create(DummyPath.class, new Locale("pt", "BR"));
	}

	@Test
	public void testGet() throws Exception {
		String value = i18n.get("titan");
		assertEquals("Titan", value);
	}

	@Test
	public void testOverride() throws Exception {
		String value = i18n.override(DummySubPath.class).get("titan");
		assertEquals("Titan Sub", value);
	}

	@Test
	public void testWithParamter() throws Exception {
		String value = i18n.get("param", new String[] { "Context" });
		assertEquals("Parametro encontrado Context", value);
	}

	@Test
	public void testGetException() throws Exception {
		String value = i18n.get(DummyException.class);
		assertEquals("Found Exception for DummyException", value);
	}

	@Test
	public void testGetEnum() throws Exception {
		String value = i18n.get(DummyEnum.DUMMY);
		assertEquals("Found Enum for DUMMY", value);
	}

	@Test
	public void testGetList() throws Exception {
		List<String> itens = Lists.newArrayList("titan", "system", "process");
		String value = i18n.get(itens);
		assertEquals("Titan, Sistema, Processo", value);
	}

}
