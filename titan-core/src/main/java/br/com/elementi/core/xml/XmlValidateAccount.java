package br.com.elementi.core.xml;

import java.util.List;

import com.google.common.collect.Lists;

public class XmlValidateAccount {

	public static List<XmlNode> insert() {
		List<XmlNode> insert = Lists.newArrayList();
		insert.add(new XmlNode().add("categoryParticipant", 					"[0-9]{1,2}-[0-9]{1,6}", "/Document/AcctOpngInstr/PrvsRef/RefIssr/PrtryId"));
		insert.add(new XmlNode().add("accountRespose", 								"^([1-9])([0-9]{0,6})", "/Document/AcctOpngInstr/InvstmtAcct/Id"));
		insert.add(new XmlNode().add("accountOwner.individual.name", 			"[\\W\\w]{1,140}", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/Nm"));
		insert.add(new XmlNode().add("accountOwner.individual.gender", 			"(MALE|FEMA)", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/Gndr"));
		insert.add(new XmlNode().add("accountOwner.individual.birthDate", 		"^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/BirthDt"));
		insert.add(new XmlNode().add("accountOwner.individual.birthCity", 		"[\\w\\W]{1,30}", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/CityOfBirth"));
		insert.add(new XmlNode().add("accountOwner.individual.birthCountry", 	"[\\w\\W]{1,3}", "/Document/AcctOpngInstr/AcctPties/PmryOwnr/IndvPrsn/CtryOfBirth"));
		insert.add(new XmlNode("accountOwner.individual.address")
				.add("mainType", "[0-9]{1,2}-[0-9]{1,6}", "/Document/AcctOpngInstr/PrvsRef/RefIssr/PrtryId")
				.add("mainType", "[0-9]{1,2}-[0-9]{1,6}", "/Document/AcctOpngInstr/PrvsRef/RefIssr/PrtryId"));
		return insert;
	}

}
