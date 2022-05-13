package br.com.elementi.core.xml;

import java.util.Map;

import com.google.common.collect.Maps;

import br.com.elementi.core.xml.XmlNode.Node;

public class XmlMessage {

	public Map<String, String> validate;

	public XmlMessage() {
		validate = Maps.newHashMap();
		validate.put("categoryParticipant", "/Document/AcctOpngInstr/PrvsRef/RefIssr/PrtryId");
		validate.put("accountRespose", "/Document/AcctOpngInstr/InvstmtAcct/Id");
		validate.put("accountOwner.individual.name", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/Nm");
		validate.put("accountOwner.individual.gender", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/Gndr");
		validate.put("accountOwner.individual.birthDate", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/BirthDt");
		validate.put("accountOwner.individual.birthCity", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/CityOfBirth");
		validate.put("accountOwner.individual.birthCountry", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/CtryOfBirth");
		validate.put("accountOwner.individual.address", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/CtryOfBirth");
	}

	public String get(String path) {
		String value = validate.get(path);
		return value == null ? "XmlMessage: message not found:" : value;
	}

	public String get(Node node) {
		String path = "this".equals(node.parentPath()) ? node.reflectPath : node.parentPath() + "." + node.reflectPath;
		return get(path);
	}

}
