package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlCmeContract {

	@XmlElement(name = "CMGBExctnNb")
	protected String cmeGroup;

	@XmlElement(name = "AcctNbClrFirm")
	protected String accountInvestment;

	@XmlElement(name = "SpcfcCtrctTpSts")
	protected String situation;

	@XmlElement(name = "MsgSpcfctnCtrctAcct")
	protected String value;

}
