package br.com.elementi.test;

public class DummyPhone {

	private Integer ddd;
	private Integer number;
	private DummyCountry country;

	public DummyPhone() {
		this.ddd = 011;
		this.number = 977886655;
		this.country = new DummyCountry("BRASIL");
	}

	public DummyPhone(Integer ddd, Integer number) {
		super();
		this.ddd = ddd;
		this.number = number;
		this.country = new DummyCountry("INGLAD");
	}

	public Integer getDdd() {
		return ddd;
	}

	public Integer getNumber() {
		return number;
	}

	public DummyCountry getCountry() {
		return country;
	}

}
