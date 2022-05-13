package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.elementi.core.tools.Packager;

public class PackagerTest {

	@Test
	public void testPack() throws Exception {
		String pack = "br.com.elementi.core.tools";
		assertEquals(pack, Packager.create(this.getClass()).getPackager());
	}

	@Test
	public void testPath() throws Exception {
		String path = "br/com/elementi/core/tools/";
		assertEquals(path, Packager.create(this.getClass()).getPath());
	}

	@Test
	public void testIsAvailable() throws Exception {
		Packager upper = Packager.create(this.getClass()).upper().upper().upper();
		assertTrue(upper.isAvailable());
	}

	@Test
	public void testIsAvailableFail() throws Exception {
		Packager upper = Packager.create(this.getClass()).upper().upper().upper().upper();
		assertFalse(upper.isAvailable());
	}

}
