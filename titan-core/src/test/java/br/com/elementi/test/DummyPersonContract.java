package br.com.elementi.test;

import java.util.List;

public class DummyPersonContract implements Cloneable {

	private String nameWithoutSet;
	private String name;
	private Integer age;
	private DummyPhoneContract phoneContract;
	private DummyAddressContract address;
	private String type;
	private List<Integer> ddds;
	private List<DummyCountryContract> countries;

	public List<DummyCountryContract> getCountries() {
		return countries;
	}

	public void setCountries(List<DummyCountryContract> countries) {
		this.countries = countries;
	}

	public final String getName() {
		return name;
	}

	public String getNameWithoutSet() {
		return nameWithoutSet;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final Integer getAge() {
		return age;
	}

	public final void setAge(Integer age) {
		this.age = age;
	}

	public final DummyPhoneContract getPhoneContract() {
		return phoneContract;
	}

	public DummyPhoneContract getNull() {
		return null;
	}

	public void setNull(DummyPhoneContract contract) {
	}

	public final void setPhoneContract(DummyPhoneContract phoneContract) {
		this.phoneContract = phoneContract;
	}

	public DummyAddressContract getAddress() {
		return address;
	}

	public void setAddress(DummyAddressContract address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getDdds() {
		return ddds;
	}

	public void setDdds(List<Integer> ddds) {
		this.ddds = ddds;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
