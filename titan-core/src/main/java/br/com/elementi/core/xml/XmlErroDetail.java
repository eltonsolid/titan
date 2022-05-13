package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlErroDetail {

	@XmlElement(name = "SeqNb")
	protected String sequence;

	@XmlElement(name = "RuleId")
	protected String code;

	@XmlElement(name = "RuleDesc")
	protected String description;

}
