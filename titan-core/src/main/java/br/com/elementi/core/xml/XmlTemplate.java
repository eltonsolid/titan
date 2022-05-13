package br.com.elementi.core.xml;

public class XmlTemplate {
	public interface XmlConverter {
		public static XmlConverter DEFAULT = new XmlConverter() {

			@Override
			public Object apllay(String from, Object to) {
				return to;
			}
		};

		public Object apllay(String from, Object to);
	}

	String from;
	String to;
	XmlConverter converter = XmlConverter.DEFAULT;

	public XmlTemplate(String from, String to) {
		this.from = from;
		this.to = to;
	}

}
