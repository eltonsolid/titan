package br.com.elementi.core.xml;

import java.lang.reflect.Field;
import java.util.List;

import br.com.elementi.core.xml.XmlNode.Node;

public class XmlValidate {

	private XmlMessage message;
	private XmlException exception;

	private XmlValidate(XmlMessage message, XmlException processException) {
		this.message = message;
		this.exception = processException;
	}

	public static XmlException match(XmlMessage message, XmlException exception, Object start, List<XmlNode> XmlNodes) {
		XmlValidate xmlValidate = new XmlValidate(message, exception);
		xmlValidate.validate(start, XmlNodes);
		return xmlValidate.exception;
	}

	public static XmlException match(Object start, List<XmlNode> XmlNodes) {
		XmlValidate xmlValidate = new XmlValidate(new XmlMessage(), new XmlException());
		xmlValidate.validate(start, XmlNodes);
		return xmlValidate.exception;
	}

	private void validate(Object start, List<XmlNode> xmlNodes) {
		for (XmlNode xmlNode : xmlNodes) {
			Object base = get(start, xmlNode.reflectPath);
			if (base == null && !xmlNode.requerid) {
				break;
			}
			if (base instanceof Iterable<?>) {
				validateMany((Iterable<?>) base, xmlNode.nodes);
			} else {
				validateOne(base, xmlNode.nodes);
			}
		}
	}

	private void validateMany(Iterable<?> bases, List<Node> nodes) {
		for (Object base : bases) {
			validateOne(base, nodes);
		}
	}

	private void validateOne(Object base, List<Node> nodes) {
		for (Node node : nodes) {
			Object result = get(base, node.reflectPath);
			String value = (result == null) ? "" : "" + result;
			validateResult(node, value);
		}
	}

	private void validateResult(Node node, String value) {
		if (node.requerid && value.isEmpty()) {
			exception.add("201", message.get(node));
		} else if (!value.matches(node.regex)) {
			exception.add("201", message.get(node));
		}
	}

	private Object get(Object base, String path) {
		if (base == null) {
			return null;
		}
		if ("this".equals(path)) {
			return base;
		}
		Object from = base;
		for (String fieldName : path.split("\\.")) {
			from = get(from, fieldName, path);
			if (from == null) {
				return null;
			}
		}
		return from;
	}

	private Object get(Object from, String fieldName, String path) {
		try {
			for (Field field : from.getClass().getDeclaredFields()) {
				if (field.getName().equals(fieldName)) {
					return field.get(from);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new XmlValidateException("XmlNodeItem found invalid Field or Object on path:" + path);
		}
		throw new XmlValidateException("XmlNodeItem found invalid Field or Object on path:" + path);
	}

}
