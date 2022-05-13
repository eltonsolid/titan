package br.com.elementi.core.xml;

import br.com.elementi.core.xml.XmlResolver.XmlFileOperation;

public class XmlProcess {

	public XmlFile start(XmlFile xmlFile) throws Exception {
		XmlFileOperation operation = XmlResolver.operation(xmlFile.creator());
		switch (operation) {
		case ACCOUNT_INSERT:
			return accountInsert(xmlFile);
		case ACCOUNT_UPDATE:
			return accountUpdate(xmlFile);
		case ACCOUNT_DETAIL:
			return accountDetail(xmlFile);
		case ACCOUNT_HASH:
			return hash(xmlFile);
		case LINK_INSERT_UPDATE:
			return linkInsertUpdate(xmlFile);
		default:
			return XmlFile.erro(xmlFile.creator());
		}
	}

	private XmlFile accountInsert(XmlFile fileReceive) {
		XmlFile response = XmlFile.accountRespose(fileReceive.creator());
		for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
			response.add(new XmlProcessAccount().insert(bizGrp));
		}
		return response;
	}

	private XmlFile accountUpdate(XmlFile fileReceive) {
		XmlFile response = XmlFile.accountRespose(fileReceive.creator());
		for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
			response.add(new XmlProcessAccount().update(bizGrp));
		}
		return response;
	}

	private XmlFile accountDetail(XmlFile fileReceive) {
		XmlFile response = XmlFile.accountRespose(fileReceive.creator());
		for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
			response.add(new XmlProcessAccount().update(bizGrp));
		}
		return response;
	}

	private XmlFile hash(XmlFile fileReceive) {
		XmlFile response = XmlFile.hash(fileReceive.creator());
		for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
			response.add(XmlProcessAccount.hash(bizGrp));
		}
		return response;
	}

	private XmlFile linkInsertUpdate(XmlFile fileReceive) {
		XmlFile response = XmlFile.link(fileReceive.creator());
		for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
			response.add(new XmlProcessAccount().update(bizGrp));
		}
		return response;
	}

	private XmlFile linkDetail(XmlFile fileReceive) {
		XmlFile response = XmlFile.link(fileReceive.creator());
		for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
			response.add(new XmlProcessAccount().update(bizGrp));
		}
		return response;
	}

}
