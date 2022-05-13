package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlHashAccount {

	@XmlPath("AccInf/AcctId/Prtry/Id/text()")
	private String account;

	@XmlPath("HASHCd64/text()")
	private String hash;



}
