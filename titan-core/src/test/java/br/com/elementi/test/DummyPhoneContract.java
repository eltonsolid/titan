package br.com.elementi.test;

public class DummyPhoneContract {

	private Integer ddd;
	private Integer number;

	public DummyPhoneContract() {
	}

	public DummyPhoneContract(Integer ddd, Integer number) {
		super();
		this.ddd = ddd;
		this.number = number;
	}

	public Integer getDdd() {
		return ddd;
	}

	public Integer getNumber() {
		return number;
	}

	public final void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public final void setNumber(Integer number) {
		this.number = number;
	}

}
