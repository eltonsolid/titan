package br.com.elementi.core.xml;

import br.com.elementi.core.xml.XmlException.Value;

public class XmlProcessLink {

	public XmlBizGrp insert(XmlBizGrp bizGrp) {
		try {
			XmlLink linkAccount = bizGrp.body.linkAccount;
			validateBizGrpValues(linkAccount);


			XmlDetailLink detailLink = createDetailLink(bizGrp.header, new Link());
			return XmlBizGrp.create(bizGrp.header, detailLink);
		} catch (XmlException exception) {
			return erro(bizGrp.header, exception);
		} catch (Exception exception) {
			return erro(bizGrp.header, exception);
		}

	}

	private void validateBizGrpValues(XmlLink linkAccount) throws XmlException{
		// TODO Auto-generated method stub

	}

	private XmlDetailLink createDetailLink(XmlHeader header, Link link) {
		// TODO Auto-generated method stub
		return null;
	}

	private XmlBizGrp erro(XmlHeader header, Exception exception) {
		String description = exception.getMessage() == null ? exception.getClass().getName() : exception.getMessage();
		return erro(header, new XmlException("999", description));
	}

	private XmlBizGrp erro(XmlHeader header, XmlException exception) {
		exception.printStackTrace();
		XmlErro erro = XmlErro.create(header);
		for (Value value : exception.values) {
			erro.add(value.code, value.description);
		}
		return XmlBizGrp.create(header, erro);
	}

}
