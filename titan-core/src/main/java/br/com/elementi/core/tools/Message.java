package br.com.elementi.core.tools;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.exception.NotAllowException;
import br.com.elementi.core.message.MessageMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Message {

	private Map<String, String> properties;
	private Map<Enum<?>, String> enumerates;
	private Map<Class<? extends DomainException>, String> exceptions;

	private Message() {
		properties = Maps.newHashMap();
		enumerates = Maps.newHashMap();
		exceptions = Maps.newHashMap();
	}

	public static Message create(List<MessageTemplate> templates) throws Exception {
		Message message = new Message();
		for (MessageTemplate template : templates) {
			validateUnique(message.properties, template.properties());
			validateUnique(message.enumerates, template.enumerates());
			validateUnique(message.exceptions, template.exceptions());
			message.properties.putAll(template.properties());
			message.enumerates.putAll(template.enumerates());
			message.exceptions.putAll(template.exceptions());
		}
		return message;
	}

	private static <K, V> void validateUnique(Map<K, V> contain, Map<K, V> add) throws Exception {
		Set<Entry<K, V>> entrySet = add.entrySet();
		for (Entry<K, V> entry : entrySet) {
			if (contain.containsKey(entry.getKey())) {
				throw new NotAllowException(entry.getKey().toString());
			}
		}
	}

	public MessageMap messageMap() {
		return new MessageMap(properties);
	}

	public Message override(MessageTemplate template) {
		Message message = new Message();
		message.properties.putAll(properties);
		message.properties.putAll(template.properties());
		message.enumerates.putAll(enumerates);
		message.enumerates.putAll(template.enumerates());
		message.exceptions.putAll(exceptions);
		message.exceptions.putAll(template.exceptions());
		return message;
	}

	public String get(String key, String... arguments) {
		return inception(propertie(key), propertie(arguments));
	}

	public String get(DomainException domainException) {
		return inception(exception(domainException.getClass()), propertie(domainException.getArguments()));
	}

	public String get(Enum<?> key) throws Exception {
		return inception(enumerate(key), new String[] {});
	}

	private String propertie(String key) {
		return internationalization(properties, key);
	}

	private String exception(Class<? extends DomainException> key) {
		return internationalization(exceptions, key);
	}

	private String enumerate(Enum<?> key) {
		return internationalization(enumerates, key);
	}

	private <K, V> String internationalization(Map<K, String> itens, Object key) {
		if (itens.containsKey(key)) {
			return itens.get(key);
		}
		return key.toString();
	}

	private Object[] propertie(String[] arguments) {
		List<String> values = Lists.newArrayList();
		for (String key : arguments) {
			values.add(get(key));
		}
		return values.toArray();
	}

	private String inception(String pattern, Object[] arguments) {
		Object[] values = pattern.contains("{" + arguments.length + "}") ? arguments : join(arguments);
		return MessageFormat.format(pattern, values);
	}

	private Object[] join(Object[] arguments) {
		String dot = "";
		StringBuilder join = new StringBuilder();
		for (Object argument : arguments) {
			join.append(dot).append(argument);
			dot = ", ";
		}
		return new Object[] { join };
	}
}
