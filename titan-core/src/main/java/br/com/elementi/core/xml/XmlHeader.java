package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlHeader {

	@XmlElement(name = ".")
	protected XmlHeaderAttribute attr = new XmlHeaderAttribute();

	@XmlElement(name = ".")
	protected XmlHeaderValue creator;

	@XmlElement(name = "Rltd")
	protected XmlHeaderValue sender;

	public static XmlHeader create(String messageIdentification, String messageType, XmlHeader sender) {
		XmlHeader header = create("BVMF", messageIdentification, messageType);
		header.creator.to = sender.creator.from;
		header.sender = sender.creator;
		return header;
	}

	public static XmlHeader create(String identification, String messageIdentification, String messageType) {
		XmlHeader header = new XmlHeader();
		header.creator = new XmlHeaderValue();
		header.creator.messageIdentification = messageIdentification;
		header.creator.messageType = messageType;
		header.creator.created = ISODateTimeFormat.dateTimeNoMillis().print(DateTime.now());
		header.creator.from = new XmlFromTo();
		header.creator.from.identification = identification;
		return header;
	}

}
