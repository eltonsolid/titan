package br.com.elementi.core.domain;

import java.io.Serializable;
import java.util.Objects;

import br.com.elementi.core.model.Identity;

public abstract class DomainEntity implements Comparable<DomainEntity>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1789247835115101429L;

	public abstract Identity getId();

	protected Integer getUnique() {
		return 0;
	}

	protected String appendToString() {
		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getUnique() == null) ? 0 : getUnique().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof DomainEntity ? this.hashCode() == obj.hashCode() : false;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " :: " + getId() + " :: " + appendToString();
	}

	public int compareTo(DomainEntity that) {
		return this.getId().compareTo(that.getId());
	}

}
