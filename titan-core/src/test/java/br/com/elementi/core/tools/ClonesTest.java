package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.test.DummyPersonContract;
import br.com.elementi.test.DummyPhoneContract;

public class ClonesTest extends Unit {

	private DummyPersonContract contract;

	@Before
	public void before() throws Exception {
		contract = new DummyPersonContract();
		DummyPhoneContract phoneContract = new DummyPhoneContract();
		phoneContract.setNumber(10);
		contract.setPhoneContract(phoneContract);
	}

	@Test
	public void testClone() throws Exception {
		DummyPersonContract clone = Clones.clone(contract);
		clone.getPhoneContract().setNumber(20);
		assertFalse(clone.getPhoneContract().getNumber().equals(contract.getPhoneContract().getNumber()));
	}

	@Test
	public void testCloneNotChange() throws Exception {
		DummyPersonContract clone = Clones.clone(contract);
		assertTrue(clone.getPhoneContract().getNumber().equals(contract.getPhoneContract().getNumber()));
	}

}
