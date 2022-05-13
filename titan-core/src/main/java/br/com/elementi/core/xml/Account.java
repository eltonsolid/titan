package br.com.elementi.core.xml;

import java.util.List;

public class Account {

	private Integer code;

	private String number;

	private String operacionalCode;

	private String category;

	private Integer entity;

	private Integer participantEntity;

	private List<AccountAddress> address;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
