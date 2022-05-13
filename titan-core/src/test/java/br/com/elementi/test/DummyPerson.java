package br.com.elementi.test;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;

@Entity
@EntityListeners(value = {  })
public class DummyPerson extends DummyPersonSuper{

	public static String HIDE_PERSON = "HidePerson";

	@Id
	private Integer id;
	@JoinColumn
	private DummyEntity entity;
	private String nameWithoutGet;
	private String name;
	private Integer age;
	private DummyPersonType type;
	private DateTime created;
	private Boolean individual;
	private DummyAddress address;
	private List<DummyPhone> phones;
	private DummyPhone phone;
	private DummyContract contract;
	private DummyContact contact;

	public DummyPerson() {
		this.id = 1;
		this.nameWithoutGet = HIDE_PERSON;
		this.name = "Person";
		this.age = 30;
		this.type = DummyPersonType.CPF;
		this.created = DateTime.now();
		this.individual = Boolean.TRUE;
		this.address = new DummyAddress();
		this.phones = Lists.newArrayList(new DummyPhone(), new DummyPhone(012, 00000000));
		// this.phone = new DummyPhone();
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public DummyPersonType getType() {
		return type;
	}

	public DateTime getCreated() {
		return created;
	}

	public Boolean getIndividual() {
		return individual;
	}

	public DummyAddress getAddress() {
		return address;
	}

	public DummyAddress getAnotherAddress() {
		return address;
	}

	public List<DummyPhone> getPhones() {
		return phones;
	}

	public DummyPhone getPhone() {
		return phone;
	}

	public DummyContract getContract() {
		return contract;
	}

	public DummyContact getContact() {
		return contact;
	}

	public DummyPerson getNullDummyPerson() {
		return null;
	}
}
