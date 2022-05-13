package br.com.elementi.core.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;

import br.com.elementi.test.DummyPath;

public class BundleTest {

	private Packager packager;
	private Locale locale;

	@Before
	public void before() throws Exception {
		packager = Packager.create(DummyPath.class);
		locale = new Locale("pt", "BR");
	}

	@Test
	public void testLoadMessage() throws Exception {
		Bundle bundle = Bundle.message(packager, locale);
		assertTrue(bundle.containsKey("titan"));
	}

	@Test
	public void testLoadMessageEnUS() throws Exception {
		Bundle bundle = Bundle.message(packager, new Locale("en", "US"));
		assertEquals("Titan english", bundle.get("titan"));
	}

	@Test
	public void testLoadMessageDouble() throws Exception {
		HashMap<String, String> parent = Maps.newHashMap();
		parent.put("titan", "TEST");
		Bundle bundle = Bundle.message(packager, locale, parent);
		assertEquals("TEST", bundle.get("titan"));
	}

	@Test
	public void testLoadMessageFail() throws Exception {
		Bundle bundle = Bundle.message(packager.upper(), locale);
		assertFalse(bundle.containsKey("titan"));
	}

	@Test
	public void testLoadException() throws Exception {
		Bundle bundle = Bundle.exception(packager, locale);
		assertTrue(bundle.containsKey("DummyException"));
	}

	@Test
	public void testLoadExceptionFail() throws Exception {
		Bundle bundle = Bundle.exception(packager.upper(), locale);
		assertFalse(bundle.containsKey("DummyPath"));
	}

	@Test
	public void testLoadEnumeration() throws Exception {
		Bundle bundle = Bundle.enumerate(packager, locale);
		assertTrue(bundle.containsKey("DummyEnum.DUMMY"));
	}

	@Test
	public void testLoadEnumerationFail() throws Exception {
		Bundle bundle = Bundle.enumerate(packager.upper(), locale);
		assertFalse(bundle.containsKey("Enum"));
	}

}
