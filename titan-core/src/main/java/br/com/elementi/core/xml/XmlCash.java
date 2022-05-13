package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlCash {

	@XmlElement(name = "XtndedTp")
	protected String type;

	@XmlElement(name = "CshAcctClssfctnPurp")
	protected String classification;

	@XmlElement(name = "CshAcctPurp")
	protected String purpose;

	@XmlElement(name = "Ccy")
	protected String currency;

	@XmlPath("Svcr/BrnchId/Id/text()")
	protected String number;

	@XmlPath("Svcr/FinInstnId/BIC/text()")
	protected String institution;

}
