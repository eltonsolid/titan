package br.com.elementi.core.tools;

import org.junit.Test;


public class DocumentValidateTest {


	@Test
	public void testIsCPF() throws Exception {
		DocumentValidate.isCPF("321.845");
	}



}
