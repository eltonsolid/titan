package br.com.elementi.test;

import br.com.elementi.core.exception.NotFoundException;

public class DummyWatcher {

	private int count;
	private Exception exception;

	private DummyWatcher() {
	}

	private DummyWatcher(Exception exception) {
		this.exception = exception;
		count++;
	}
	private DummyWatcher(Exception exception, String... arguments) {
		this.exception = exception;
		count++;
	}

	public static DummyWatcher create() {
		return new DummyWatcher();
	}

	public void count() {
		count++;
	}

	public int getCount() {
		return count;
	}

	public void exception() throws Exception {
		throw new Exception();
	}

	public void domainException() throws Exception {
		throw new NotFoundException();
	}

	public Exception getException() {
		return exception;
	}

}
