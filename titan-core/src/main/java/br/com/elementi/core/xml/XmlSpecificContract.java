package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSpecificContract {

	@XmlElement(name = "OrdrRtgBVMFToCME")
	protected XmlCmeContract cme;

	@XmlElement(name = "Trsr")
	protected XmlTreasuryContract treasury;

	@XmlElement(name = "SmpdRegn")
	protected XmlSimpleRegisterContract simpleRegister;

}
