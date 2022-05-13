package br.com.elementi.core.tools;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import com.google.common.collect.Sets;

public class Bundle extends HashMap<String, String> {
	/**
	 *
	 */
	private static final long serialVersionUID = 2467608567494166994L;
	private static final String NOT_EXIST = "NONEXISTENT_BUNDLE";
	private static final String MESSAGE = "message";
	private static final String ENUM = "enum";
	private static final String EXCEPTION = "exception";
	private final String resource;
	private Locale locale;

	public Bundle(String resource, Locale locale) {
		this.resource = resource;
		this.locale = locale;
	}

	public static Bundle message(Packager packager, Locale locale) {
		return new Bundle(MESSAGE, locale).search(packager);
	}

	public static Bundle message(Packager packager, Locale locale, HashMap<String, String> parent) {
		return new Bundle(MESSAGE, locale).parent(packager, parent);
	}

	public static Bundle enumerate(Packager packager, Locale locale) {
		return new Bundle(ENUM, locale).search(packager);
	}

	public static Bundle enumerate(Packager packager, Locale locale, HashMap<String, String> parent) {
		return new Bundle(ENUM, locale).parent(packager, parent);
	}

	public static Bundle exception(Packager packager, Locale locale) {
		return new Bundle(EXCEPTION, locale).search(packager);
	}

	public static Bundle exception(Packager packager, Locale locale, HashMap<String, String> parent) {
		return new Bundle(EXCEPTION, locale).parent(packager, parent);
	}

	private Bundle parent(Packager packager, HashMap<String, String> parent) {
		this.putAll(parent);
		return search(packager);
	}

	private Bundle search(Packager packager) {
		search0(packager);
		return this;
	}

	private void search0(Packager packager) {
		ResourceBundle bundle = bundle(packager.getPath());
		put(bundle);
		if (packager.isAvailable()) {
			search0(packager.upper());
		}

	}

	private void put(ResourceBundle bundle) {
		for (String key : bundle.keySet()) {
			if (!this.containsKey(key)) {
				this.put(key, bundle.getString(key));
			}
		}
	}

	private ResourceBundle bundle(String basename) {
		try {
			return ResourceBundle.getBundle(basename + resource, locale);
		} catch (Exception e) {
			return nullBundle();
		}
	}

	private ResourceBundle nullBundle() {
		return new ResourceBundle() {
			@Override
			protected Set<String> handleKeySet() {
				return Sets.newHashSet();
			}

			public Enumeration<String> getKeys() {
				return null;
			}

			protected Object handleGetObject(String key) {
				return null;
			}

			public String toString() {
				return NOT_EXIST;
			}
		};
	}

}
