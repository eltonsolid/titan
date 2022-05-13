package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlBizGrp {

	@XmlElement(name = "AppHdr")
	protected XmlHeader header;

	@XmlElement(name = "Document")
	protected XmlBody body;

	public static XmlBizGrp create(XmlHeader sender, XmlDetailAccount detailAccount) {
		XmlBizGrp bizGrp = new XmlBizGrp();
		bizGrp.header = XmlHeader.create("-1", XmlResolver.ACCOUNT_DEFINATION, sender);
		bizGrp.body = new XmlBody();
		bizGrp.body.attr = XmlBodyAttribute.detailAccount();
		bizGrp.body.detailAccount = detailAccount;
		return bizGrp;
	}

	public static XmlBizGrp create(XmlHeader sender, XmlDetailLink detailLink) {
		XmlBizGrp bizGrp = new XmlBizGrp();
		bizGrp.header = XmlHeader.create("-1", XmlResolver.LINK_DEFINATION, sender);
		bizGrp.body = new XmlBody();
		bizGrp.body.attr = XmlBodyAttribute.detailLink();
		bizGrp.body.detailLink = detailLink;
		return bizGrp;
	}

	public static XmlBizGrp create(XmlHeader sender, XmlDetailHash detailHash) {
		XmlBizGrp bizGrp = new XmlBizGrp();
		bizGrp.header = XmlHeader.create("-1", XmlResolver.HASH_DEFINATION, sender);
		bizGrp.body = new XmlBody();
		bizGrp.body.attr = XmlBodyAttribute.detailHash();
		bizGrp.body.detailHash = detailHash;
		return bizGrp;
	}

	public static XmlBizGrp create(XmlHeader sender, XmlErro erro) {
		XmlBizGrp bizGrp = new XmlBizGrp();
		bizGrp.header = XmlHeader.create("-1", XmlResolver.ERRO_DEFINATION, sender);
		bizGrp.body = new XmlBody();
		bizGrp.body.attr = XmlBodyAttribute.erro();
		bizGrp.body.erro = erro;
		return bizGrp;
	}

}
