package br.com.elementi.test;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.joda.time.DateTime;

public class DummyContract {

	@Id
	private String code;

	private String type;

	@JoinColumn
	private DummyPerson person;

	@Column
	private DummyIdentityEntity identityEntity;

	private DateTime created;
	private DummyPerson owner;

}
