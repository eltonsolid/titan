package br.com.elementi.core.mapper;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.elementi.core.Unit;
import br.com.elementi.test.DummyAddress;
import br.com.elementi.test.DummyAddressContract;
import br.com.elementi.test.DummyCountry;
import br.com.elementi.test.DummyCountryContract;
import br.com.elementi.test.DummyExtendsPerson;
import br.com.elementi.test.DummyExtendsPersonContract;
import br.com.elementi.test.DummyPerson;
import br.com.elementi.test.DummyPersonContract;

public class MapperTest extends Unit {

	private Mapper mapper;
	private DummyPerson person;
	private DummyExtendsPerson extendsPerson;
	private MapperTemplate template;
	private MapperTemplate extendsTemplate;

	@Before
	public void before() throws Exception {
		mapper = Mapper.start();
		person = new DummyPerson();
		template = MapperTemplate.with(DummyPerson.class, DummyPersonContract.class);
		extendsPerson = new DummyExtendsPerson();
		extendsTemplate = MapperTemplate.with(DummyExtendsPerson.class, DummyPersonContract.class, template);
	}

	@Test
	public void testFind() throws Exception {
		mapper.add(template);
		List<MapperTemplate> found = mapper.listFor(DummyPerson.class);
		assertEquals(DummyPerson.class, found.get(0).getFrom());
	}

	@Test
	public void testAddTemplateSameTemplate() throws Exception {
		mapper.add(template);
		mapper.add(template);
		List<MapperTemplate> found = mapper.listFor(DummyPerson.class);
		assertEquals(ONE.intValue(), found.size());
	}

	@Test
	public void testAddTemplateAnotherTemplate() throws Exception {
		mapper.add(template);
		mapper.add(MapperTemplate.with(DummyPerson.class, DummyPerson.class));
		List<MapperTemplate> found = mapper.listFor(DummyPerson.class);
		assertEquals(TWO.intValue(), found.size());
	}

	@Test
	public void testFindForEmptyItens() throws Exception {
		List<MapperTemplate> found = mapper.listFor(DummyPerson.class);
		assertEquals(ZERO.intValue(), found.size());
	}

	@Test
	public void testMap() throws Exception {
		mapper.add(template.add("name", "name"));
		DummyPersonContract mapped = mapper.mapping(person, DummyPersonContract.class);
		assertEquals(person.getName(), mapped.getName());
	}

	@Test
	public void testMapOnList() throws Exception {
		mapper.add(template.add("name", "name"));
		List<DummyPerson> itens = Lists.newArrayList(person, person);
		List<DummyPersonContract> contracts = mapper.mapping(itens, DummyPersonContract.class);
		assertEquals(itens.get(0).getName(), contracts.get(0).getName());
	}

	@Test
	public void testMapResolverOnTemplate() throws Exception {
		mapper.add(MapperTemplate.with(DummyAddress.class, DummyAddressContract.class).add("street", "description"));
		mapper.add(template.add("name", "name"));
		mapper.add(template.add("address", "address"));
		DummyPersonContract mapped = mapper.mapping(person, DummyPersonContract.class);
		assertEquals(person.getAddress().getStreet(), mapped.getAddress().getDescription());
	}

	@Test
	public void testMapResolverOnTemplateForList() throws Exception {
		mapper.add(MapperTemplate.with(DummyCountry.class, DummyCountryContract.class).add("name", "description"));
		mapper.add(template.add("phones.country", "countries"));
		DummyPersonContract mapped = mapper.mapping(person, DummyPersonContract.class);
		assertEquals(person.getPhones().get(0).getCountry().getName(), mapped.getCountries().get(0).getDescription());
	}

	@Test
	public void testMapResolverOnTemplateForListItemTwo() throws Exception {
		mapper.add(MapperTemplate.with(DummyCountry.class, DummyCountryContract.class).add("name", "description"));
		mapper.add(template.add("phones.country", "countries"));
		DummyPersonContract mapped = mapper.mapping(person, DummyPersonContract.class);
		assertEquals(person.getPhones().get(1).getCountry().getName(), mapped.getCountries().get(1).getDescription());
	}

	@Test
	public void testPersonExtended() throws Exception {
		template.add("name", "name");
		mapper.add(extendsTemplate);
		DummyPersonContract mapped = mapper.mapping(extendsPerson, DummyPersonContract.class);
		assertEquals(extendsPerson.getName(), mapped.getName());
	}

	@Test
	public void testWhereContractWasExtended() throws Exception {
		template.add("name", "name");
		MapperTemplate with = MapperTemplate.with(DummyExtendsPerson.class, DummyExtendsPersonContract.class, template);
		mapper.add(with.add("extendName", "legalName"));
		DummyExtendsPersonContract mapped = mapper.mapping(extendsPerson, DummyExtendsPersonContract.class);
		assertEquals(extendsPerson.getExtendName(), mapped.getLegalName());
	}

}
