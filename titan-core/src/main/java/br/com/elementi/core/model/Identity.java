package br.com.elementi.core.model;

import java.io.Serializable;

import br.com.elementi.core.annotation.IdentityDefine;
import br.com.elementi.core.constraint.Constraints;

@IdentityDefine
public class Identity implements Comparable<Identity>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5364180609200690698L;
	public static final Identity ZERO = new Identity(0);
	public static final Identity ONE = new Identity(1);
	public static final Identity TWO = new Identity(2);

	private Integer value;

	private Identity() {
	}

	protected Identity(Integer id) {
		this();
		this.value = id;
	}

	public static Identity create(Integer value) throws Exception {
		Constraints.notAllow(value < 0);
		return new Identity(value);
	}

	public int compareTo(Identity that) {
		return this.value.compareTo(that.value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Identity) {
			Identity that = (Identity) obj;
			return this.value.equals(that.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public final Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {" + value + "}";
	}

}
