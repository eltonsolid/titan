package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;

import br.com.elementi.core.Unit;
import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.mapper.MapperField;
import br.com.elementi.core.model.ReflectDefaultValue;
import br.com.elementi.core.tools.Reflect.ReflectPath;
import br.com.elementi.test.DummyAddress;
import br.com.elementi.test.DummyContract;
import br.com.elementi.test.DummyEntity;
import br.com.elementi.test.DummyPerson;
import br.com.elementi.test.DummyPersonContract;
import br.com.elementi.test.DummyPhoneContract;
import br.com.elementi.test.DummyQSearch;
import br.com.elementi.test.DummyWatcher;
import javassist.util.proxy.MethodHandler;

public class ReflectTest extends Unit {

	private DummyPerson person;
	private DummyPersonContract contract;
	private MapperField field;
	private static final ReflectDefaultValue REFLECT_DEFAULT_VALUE = new ReflectDefaultValue() {

		public Object reflectDefaultValue(Class<?> type) throws Exception {
			return type.newInstance();
		}
	};

	@Before
	public void before() throws Exception {
		field = MapperField.onGet("name");
		person = new DummyPerson();
		contract = new DummyPersonContract();
	}

	@Test
	public void testInstance() throws Exception {
		DummyPerson person = (DummyPerson) Reflect.instance(DummyPerson.class);
		assertNotNull(person);
	}

	@Test
	public void testInstanceWithParamters() throws Exception {
		DummyWatcher dummyWatcher = (DummyWatcher) Reflect.instance(DummyWatcher.class, new Exception());
		assertNotNull(dummyWatcher.getException());
	}

	@Test
	public void testDefaultValueForDouble() throws Exception {
		Double defaultValue = (Double) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, Double.class);
		Double negativeInfinity = Double.MIN_VALUE;
		assertEquals(negativeInfinity, defaultValue);
	}

	@Test
	public void testDefaultValueInteger() throws Exception {
		Integer defaultValue = (Integer) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, Integer.class);
		Integer negativeInfinity = Integer.MIN_VALUE;
		assertEquals(negativeInfinity, defaultValue);
	}
	
	@Ignore
	@Test
	public void testDefaultValueDate() throws Exception {
		Date defaultValue = (Date) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, Date.class);
		Date dateNow = DateTime.now().toDate();
		assertEquals(dateNow, defaultValue);
	}
	
	@Test
	public void testDefaultValueDateTime() throws Exception {
		DateTime defaultValue = (DateTime) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, DateTime.class);
		DateTime dateNow = DateTime.now();
		assertEquals(dateNow.toDate(), defaultValue.toDate());
	}

	@Test
	public void testDefaultValueList() throws Exception {
		List<?> defaultValue = (List<?>) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, List.class);
		List<?> list = Collections.EMPTY_LIST;
		assertEquals(list, defaultValue);
	}

	@Test
	public void testDefaultValueSet() throws Exception {
		Set<?> defaultValue = (Set<?>) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, Set.class);
		Set<?> value = Collections.EMPTY_SET;
		assertEquals(value, defaultValue);
	}

	@Test
	public void testDefaultValueMap() throws Exception {
		Map<?, ?> defaultValue = (Map<?, ?>) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, Map.class);
		Map<?, ?> value = Collections.EMPTY_MAP;
		assertEquals(value, defaultValue);
	}

	@Test
	public void testDefaultValueObject() throws Exception {
		Reflect defaultValue = (Reflect) Reflect.defaultValue(REFLECT_DEFAULT_VALUE, Reflect.class);
		assertNotNull(defaultValue);
	}

	@Test
	public void testDefaultClass() throws Exception {
		Class<?> classe = Reflect.defaultClass(ArrayList.class);
		assertTrue(List.class.equals(classe));
	}

	@Test
	public void testHasField() throws Exception {
		assertTrue(Reflect.hasField(DummyPerson.class, "name"));
	}

	@Test
	public void testHasFieldFail() throws Exception {
		assertFalse(Reflect.hasField(DummyPerson.class, "test"));
	}

	@Test
	public void testHasFieldInObject() throws Exception {
		assertTrue(Reflect.hasField(DummyPerson.class, "address.street"));
	}

	@Test
	public void testHasFieldInObjectFail() throws Exception {
		assertFalse(Reflect.hasField(DummyPerson.class, "address.add"));
	}

	@Test
	public void testGetFieldNameFrom() throws Exception {
		ReflectPath relational = Reflect.findBound(DummyEntity.class, DummyContract.class);
		assertEquals("id", relational.fromName());
	}

	@Test
	public void testGetFieldNameTo() throws Exception {
		ReflectPath relational = Reflect.findBound(DummyEntity.class, DummyContract.class);
		assertEquals("identityEntity", relational.toName());
	}

	@Test
	public void testGetFieldNameToWithNoIdentity() throws Exception {
		ReflectPath relational = Reflect.findBound(DummyEntity.class, DummyPerson.class);
		assertEquals("entity.id", relational.toName());
	}

	@Test
	public void testGetValueFrom() throws Exception {
		assertEquals(person.getName(), Reflect.getValueFrom(person, field));
	}

	@Test
	public void testGetValueFromWithString() throws Exception {
		assertEquals(person.getName(), Reflect.getValueFrom(person, "name"));
	}

	@Test
	public void testGetValueFromField() throws Exception {
		assertEquals(DummyPerson.HIDE_PERSON, Reflect.getValueFrom(person, MapperField.onField("nameWithoutGet")));
	}

	@Test(expected = NotFoundException.class)
	public void testGetValueFromFieldFail() throws Exception {
		assertEquals(DummyPerson.HIDE_PERSON, Reflect.getValueFrom(person, MapperField.onField("nameWithoutGet$$$")));
	}

	@Test
	public void testGetValueFromObject() throws Exception {
		assertEquals(person.getAddress().getStreet(), Reflect.getValueFrom(person, "address.street"));
	}

	@Test
	public void testGetValueFromObjectWithAnotherGet() throws Exception {
		assertEquals(person.getAddress().getStreet(), Reflect.getValueFrom(person, "anotherAddress.street"));
	}

	@Test
	public void testGetValueFromIterableObject() throws Exception {
		List<DummyPerson> persons = Lists.newArrayList(person, person);
		@SuppressWarnings("unchecked")
		List<String> names = (List<String>) Reflect.getValueFrom(persons, "name");
		assertEquals(TWO.intValue(), names.size());
	}

	@Test
	public void testGetValueFromDotIterableObject() throws Exception {
		@SuppressWarnings("unchecked")
		List<String> names = (List<String>) Reflect.getValueFrom(person, "phones.ddd");
		assertEquals(TWO.intValue(), names.size());
	}

	@Test
	public void testGetValueFromSuper() throws Exception {
		assertEquals(person.getThatName(), Reflect.getValueFrom(person, "thatName"));
	}

	@Test(expected = NotFoundException.class)
	public void testGetValueFromFail() throws Exception {
		Reflect.getValueFrom(person, "thatNames");
	}

	@Test
	public void testNameWithoutGet() throws Exception {
		String nameWithoutGet = (String) Reflect.getValueFrom(person, MapperField.onField("nameWithoutGet"));
		assertEquals("HidePerson", nameWithoutGet);
	}

	@Test
	public void testSetValueTo() throws Exception {
		String expected = "valueFromTest";
		Reflect.setValueTo(contract, field, expected);
		assertEquals(expected, contract.getName());
	}

	@Test
	public void testSetValueToWithField() throws Exception {
		String expected = "nameWithoutSet";
		Reflect.setValueTo(contract, MapperField.onField("nameWithoutSet"), expected);
		assertEquals(expected, contract.getNameWithoutSet());
	}

	@Test
	public void testSetValueToObject() throws Exception {
		Integer expected = 100;
		Reflect.setValueTo(contract, MapperField.onGet("phoneContract.ddd"), expected);
		assertEquals(expected, contract.getPhoneContract().getDdd());
	}

	@Test
	public void testSetValueOnNullValue() throws Exception {
		Reflect.setValueTo(contract, MapperField.onGet("phoneContract.ddd"), null);
		assertNull(contract.getPhoneContract().getDdd());
	}

	@Test
	public void testResolver() throws Exception {
		MapperField from = MapperField.onGet("name");
		MapperField to = MapperField.onGet("name");
		Reflect.resolver(person, from, contract, to);
		assertEquals(person.getName(), contract.getName());
	}

	@Test
	public void testResolverInObject() throws Exception {
		MapperField from = MapperField.onGet("address.street");
		MapperField to = MapperField.onGet("address.description");
		Reflect.resolver(person, from, contract, to);
		assertEquals(person.getAddress().getStreet(), contract.getAddress().getDescription());
	}

	@Test
	public void testResolverWithConverter() throws Exception {
		Reflect.resolver(person, MapperField.onGet("type"), contract, MapperField.onGet("type").with(converter -> converter.toString()));
		assertEquals(person.getType().toString(), contract.getType());
	}

	@Test
	public void testResolverForValueInList() throws Exception {
		Reflect.resolver(person, "phones.ddd", contract, "ddds");
		assertEquals(person.getPhones().size(), contract.getDdds().size());
	}

	@Test
	public void testResolverForValueInListLookEachItem() throws Exception {
		Reflect.resolver(person, "phones.ddd", contract, "ddds");
		for (int count = 0; count < person.getPhones().size(); count++) {
			assertEquals(person.getPhones().get(count).getDdd(), contract.getDdds().get(count));
		}
	}

	@Test
	public void testFindDestinationClass() throws Exception {
		Class<?> found = Reflect.findDestinationClass(DummyPersonContract.class, "phoneContract");
		assertEquals(DummyPhoneContract.class, found);
	}

	@Test
	public void testFindDestinationClassInside() throws Exception {
		Class<?> found = Reflect.findDestinationClass(DummyPersonContract.class, "phoneContract.number");
		assertEquals(Integer.class, found);
	}

	@Test
	public void testForceInstance() throws Exception {
		DummyWatcher dummyWatcher = Reflect.forceInstance(DummyWatcher.class);
		assertNotNull(dummyWatcher);
	}

	@Test
	public void testProxyClass() throws Exception {
		Class<DummyPerson> person = Reflect.proxyClass(DummyPerson.class);
		assertTrue((person.getSuperclass().isAssignableFrom(DummyPerson.class)));
	}

	@Test
	public void testProxyInstance() throws Exception {
		DummyPerson person = Reflect.proxyInstance(DummyPerson.class, new MethodHandler() {
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				return "PROXY";
			}
		});
		assertEquals("PROXY", person.getName());
	}

	@Test
	public void testReflectField() throws Exception {
		List<ReflectValue> reflectFields = Reflect.allFieldValuesOnBean(new DummyQSearch());
		assertEquals(TREE.intValue(), reflectFields.size());
	}

	@Test
	public void testReflectPath() throws Exception {
		ReflectPath path = Reflect.findBound(DummyEntity.class, DummyContract.class);
		assertEquals(path.fromName(), "id");
		assertEquals(path.toName(), "identityEntity");
	}

	@Test
	public void testReflectPathInsideClasse() throws Exception {
		ReflectPath path = Reflect.findBound(DummyEntity.class, DummyPerson.class);
		assertEquals(path.fromName(), "id");
		assertEquals(path.toName(), "entity.id");
	}

	@Test(expected = NotFoundException.class)
	public void testReflectPathNotFound() throws Exception {
		Reflect.findBound(DummyEntity.class, DummyAddress.class);
	}

}
