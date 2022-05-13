package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlBody {

	@XmlElement(name = ".")
	protected XmlBodyAttribute attr;

	@XmlElement(name = "AcctOpngInstr")
	protected XmlCreateAccount createAccount;

	@XmlElement(name = "AcctModInstr")
	protected XmlUpdateAccount updateAccount;

	@XmlElement(name = "AcctLkInstr")
	protected XmlLink linkAccount;

	@XmlElement(name = "AcctHASHReq")
	protected XmlHash hash;

	@XmlElement(name = "AcctDtlsConf")
	protected XmlDetailAccount detailAccount;

	@XmlElement(name = "AcctLkDtls")
	protected XmlDetailLink detailLink;

	@XmlElement(name = "AcctHASHRspn")
	protected XmlDetailHash detailHash;

	@XmlElement(name = "ErrRpt")
	protected XmlErro erro;

}
