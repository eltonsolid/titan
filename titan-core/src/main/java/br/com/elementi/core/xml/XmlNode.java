package br.com.elementi.core.xml;

import java.util.List;

import com.google.common.collect.Lists;

public class XmlNode {

	public class Node {
		boolean requerid;
		String reflectPath;
		String regex;
		String xmlPath;

		public Node(String reflectPath, String regex, String xmlPath) {
			this.reflectPath = reflectPath;
			this.regex = regex;
			this.xmlPath = xmlPath;
		}

		public Node requerid() {
			this.requerid = true;
			return this;
		}

		public String parentPath() {
			return XmlNode.this.reflectPath;
		}

	}

	String reflectPath;
	boolean requerid;
	List<Node> nodes = Lists.newArrayList();

	public XmlNode() {
		this.reflectPath = "this";
	}

	public XmlNode(String reflectPathBase) {
		this.reflectPath = reflectPathBase;
	}

	public XmlNode add(String reflectPath, String regex, String xmlPath) {
		nodes.add(new Node(reflectPath, regex, xmlPath));
		return this;
	}

	public XmlNode requerid(String reflectPath, String regex, String xmlPath) {
		nodes.add(new Node(reflectPath, regex, xmlPath).requerid());
		return this;
	}

}
