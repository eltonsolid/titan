package br.com.elementi.core.tools;

import static br.com.elementi.core.constraint.Constraints.notNull;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.elementi.core.constraint.Constraints;
import br.com.elementi.core.domain.DomainException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class I18n {

	private Locale locale;
	private Packager packager;
	private Map<String, String> properties;
	private Map<String, String> exceptions;
	private Map<String, String> enumerates;

	public I18n() {
		properties = Maps.newHashMap();
		exceptions = Maps.newHashMap();
		enumerates = Maps.newHashMap();
	}

	private I18n(Packager packager, Locale locale) {
		this();
		this.packager = packager;
		this.locale = locale;
	}

	private I18n load() throws Exception {
		return load(packager);
	}

	private I18n load(Packager packager) throws Exception {
		properties.putAll(Bundle.message(packager, locale));
		exceptions.putAll(Bundle.exception(packager, locale));
		enumerates.putAll(Bundle.enumerate(packager, locale));
		return this;
	}

	public I18n override(Class<?> classe) throws Exception {
		I18n newOne = new I18n(packager, locale);
		newOne.properties.putAll(properties);
		newOne.exceptions.putAll(exceptions);
		newOne.enumerates.putAll(enumerates);
		newOne.load(Packager.create(classe));
		return newOne;
	}

	public static I18n create(Class<?> classe, Locale locale) throws Exception {
		return new I18n(Packager.create(classe), notNull(locale)).load();
	}

	public String get(String key, String... arguments) throws Exception {
		return inception(propertie(notNull(key)), propertie(arguments));
	}

	public String get(List<String> keys) throws Exception {
		Constraints.notEmpty(keys);
		String values = propertie(notNull(keys.remove(0)));
		for (String key : keys) {
			values += ", " + propertie(notNull(key));
		}
		return values;
	}

	public String get(Class<? extends DomainException> classe, String... arguments) throws Exception {
		String key = notNull(classe).getSimpleName();
		return inception(exception(key), propertie(arguments));
	}

	public <E extends Enum<E>> String get(E enumerate, String... arguments) throws Exception {
		String key = notNull(enumerate).getClass().getSimpleName() + "." + enumerate.name();
		return inception(enumerate(key), propertie(arguments));
	}

	private String propertie(String key) {
		return internationalization(properties, key);
	}

	private String exception(String key) {
		return internationalization(exceptions, key);
	}

	private String enumerate(String key) {
		return internationalization(enumerates, key);
	}

	private Object[] propertie(String[] arguments) {
		LinkedList<String> values = Lists.newLinkedList();
		for (String value : arguments) {
			values.add(propertie(value));
		}
		return values.toArray();
	}

	private String internationalization(Map<String, String> itens, String key) {
		if (itens.containsKey(key)) {
			return itens.get(key);
		}
		return key;
	}

	private String inception(String pattern, Object[] arguments) {
		return MessageFormat.format(pattern, arguments);
	}

	public Locale getLocale() {
		return locale;
	}

}
