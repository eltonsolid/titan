package br.com.elementi.test;

import br.com.elementi.core.annotation.QSearchEqual;
import br.com.elementi.core.annotation.QSearchLike;
import br.com.elementi.core.domain.DomainEntity;
import br.com.elementi.core.qtable.QSearch;

public class DummyQSearch implements QSearch {

	@QSearchLike("name")
	private String name = "Eltonsolid";

	@QSearchEqual("age")
	private Integer age = 30;

	@QSearchEqual("contact.number")
	private Integer contactNumber = 991676621;

	public Class<? extends DomainEntity> from() throws Exception {
		return DummyPerson.class; 
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getContactNumber() {
		return contactNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
