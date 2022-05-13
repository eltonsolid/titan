package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSimpleRegisterContract {

	@XmlElement(name = "InitlCtrctDt")
	protected String contractStart;

	@XmlElement(name = "FnlCtrctDt")
	protected String contractEnd;

	@XmlElement(name = "PtcptIdCtrct")
	protected String contractNumber;

	@XmlElement(name = "FrgnIntdNm")
	protected String intermediatyName;

	@XmlPath("Id/XtndedIdTp/text()")
	protected String intermediatyDocumentType;

	@XmlPath("Id/Id/text()")
	protected String intermediatyDocumentNumber;

	@XmlPath("Id/Ctry/text()")
	protected String intermediatyDocumentCountry;

	@XmlElement(name = "SpcfcCtrctTpSts")
	protected String contractSituation;

}
