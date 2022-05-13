package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlHeaderValue {

	@XmlElement(name = "BizMsgIdr")
	protected String messageIdentification;

	@XmlElement(name = "MsgDefIdr")
	protected String messageType;

	@XmlElement(name = "CreDt")
	protected String created;

	@XmlElement(name = "Fr")
	protected XmlFromTo from;

	@XmlElement(name = "To")
	protected XmlFromTo to;

}
