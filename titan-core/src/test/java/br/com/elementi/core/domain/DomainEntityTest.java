package br.com.elementi.core.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

import br.com.elementi.core.Unit;
import br.com.elementi.core.model.Identity;
import br.com.elementi.test.DummyEntity;
import br.com.elementi.test.DummyIdentityEntity;

public class DomainEntityTest extends Unit {

	private DummyEntity entity;

	@Before
	public void before() throws Exception {
		entity = new DummyEntity();
		entity.id = DummyIdentityEntity.create(1);
	}

	@Test
	public void testGetId() throws Exception {
		assertEquals(Identity.create(1), entity.getId());
	}

	@Test
	public void testHascode() throws Exception {
		HashSet<DomainEntity> itens = Sets.newHashSet();
		DummyEntity entity2 = new DummyEntity();
		entity2.id = DummyIdentityEntity.create(1);
		itens.add(entity);
		itens.add(entity2);
		assertEquals(ONE.intValue(), itens.size());
	}

	@Test
	public void testHascodeTwo() throws Exception {
		HashSet<DomainEntity> itens = Sets.newHashSet();
		DummyEntity entity2 = new DummyEntity();
		entity2.id = DummyIdentityEntity.create(2);
		itens.add(entity);
		itens.add(entity2);
		assertEquals(TWO.intValue(), itens.size());
	}

}
