package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.CoreMessage;
import br.com.elementi.core.Unit;
import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.exception.MessageException;
import br.com.elementi.core.exception.NotAllowException;
import br.com.elementi.core.exception.NotFoundException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MessageTest extends Unit {

	private Message message;
	private Map<String, String> properties;

	@Before
	public void before() throws Exception {
		message = Message.create(Lists.newArrayList(new CoreMessage()));
		properties = Maps.newHashMap();
		properties.put("name", NAME);
	}

	@Test(expected = NotAllowException.class)
	public void testUnique() throws Exception {
		Message.create(Lists.newArrayList(new CoreMessage(), extracted()));
	}

	@Test
	public void testGet() throws Exception {
		String value = message.get("KEY");
		assertEquals("KEY", value);
	}

	@Test
	public void testGetWithArgument() throws Exception {
		String value = message.override(extracted()).get("test", "MESSAGE");
		assertEquals("MESSAGE was just a test", value);
	}

	@Test
	public void testGetWithArgumentTranslateParamter() throws Exception {
		String value = message.override(extracted()).get("test", "name");
		assertEquals("NAMES was just a test", value);
	}

	@Test
	public void testGetException() throws Exception {
		String value = message.get(new NotFoundException("name"));
		assertEquals("Não foi possivel encontrar Nome", value);
	}

	@Test
	public void testMessageException() throws Exception {
		MessageException messageException = new MessageException("employ");
		String value = message.get(messageException);
		assertEquals("Empresa", value);
	}

	@Test
	public void testOverride() throws Exception {
		assertEquals("Nome", message.get("name"));
		Message newMessage = message.override(extracted());
		assertEquals("NAMES", newMessage.get("name"));
	}

	private MessageTemplate extracted() {
		return new MessageTemplate() {

			@Override
			public Map<String, String> properties() {
				HashMap<String, String> itens = Maps.newHashMap();
				itens.put("client", "Client");
				itens.put("name", "NAMES");
				itens.put("test", "{0} was just a test");
				return itens;
			}

			@Override
			public Map<Class<? extends DomainException>, String> exceptions() {
				return Maps.newHashMap();
			}

			@Override
			public Map<Enum<?>, String> enumerates() {
				return Maps.newHashMap();
			}
		};
	}

}
