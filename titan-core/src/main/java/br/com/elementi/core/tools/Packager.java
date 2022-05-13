package br.com.elementi.core.tools;

import static br.com.elementi.core.constraint.Constraints.notNull;

public class Packager {

	private final String packager;

	private Packager(String packager) {
		this.packager = packager;
	}

	public static Packager create(Class<?> classe) throws Exception {
		return new Packager(notNull(classe).getPackage().getName());
	}

	public String getPath() {
		return packager.replaceAll("\\.", "/") + "/";
	}

	public Packager upper() {
		return new Packager(Regex.pack(packager).replaceFirst(""));
	}

	public String getPackager() {
		return packager;
	}

	@Override
	public String toString() {
		return "Pack: " + packager;
	}

	public Boolean isAvailable() {
		return Regex.pack(packager).find();
	}

}
