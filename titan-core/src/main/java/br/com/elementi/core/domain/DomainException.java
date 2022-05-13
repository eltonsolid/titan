package br.com.elementi.core.domain;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public abstract class DomainException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 8338795242117507417L;
	private final DomainException downException;
	private final String[] arguments;

	public DomainException(String... arguments) {
		this.downException = this;
		this.arguments = arguments;
	}

	public DomainException(DomainException exception, String... arguments) {
		this.downException = exception;
		this.arguments = arguments;
	}

	public DomainException(Exception exception, String... arguments) {
		super(exception);
		this.downException = this;
		this.arguments = arguments;
	}

	public String[] getArguments() {
		return arguments;
	}

	public List<String> getArgumentList() {
		List<String> itens = Lists.newArrayList();
		for (String argument : arguments) {
			itens.add(argument);
		}
		return itens;
	}

	@Override
	public String getMessage() {
		return Joiner.on(" - ").join(arguments);
	}

	public final DomainException getDownException() {
		return downException;
	}

	public boolean hasNext() {
		return !this.equals(downException);
	}

	public String getStack() {
		StringBuilder trace = new StringBuilder();
		level(this, trace);
		return trace.toString();
	}

	private void level(DomainException domainException, StringBuilder trace) {
		trace.append(domainException.toString());
		if (domainException.hasNext()) {
			trace.append(" :: ");
			level(domainException.downException, trace);
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " (" + getMessage() + ")";
	}
}
