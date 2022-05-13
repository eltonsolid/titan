package br.com.elementi.core.check;

@Deprecated
public interface Checkable {

	default public Class<? extends Checkable> check() {
		return this.getClass();
	}

}
