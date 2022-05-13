package br.com.elementi.core.resource;

import java.io.InputStream;

import br.com.elementi.core.tools.Regex;

import com.google.common.base.Predicate;

public class ResourceStream {

	public static final Predicate<ResourceStream> PROPERTIES = new Predicate<ResourceStream>() {
		public boolean apply(ResourceStream input) {
			return Regex.properties(input.name);
		}
	};

	public static final Predicate<ResourceStream> SQL = new Predicate<ResourceStream>() {
		public boolean apply(ResourceStream input) {
			return Regex.sql(input.getName());
		}
	};

	public static final Predicate<ResourceStream> CLASS_FILE = new Predicate<ResourceStream>() {
		public boolean apply(ResourceStream input) {
			return Regex.classFile(input.getName());
		}
	};

	private String name;
	private InputStream stream;

	public ResourceStream(String name, InputStream stream) {
		super();
		this.name = name;
		this.stream = stream;
	}

	public String getName() {
		return name;
	}

	public InputStream getStream() {
		return stream;
	}

}
