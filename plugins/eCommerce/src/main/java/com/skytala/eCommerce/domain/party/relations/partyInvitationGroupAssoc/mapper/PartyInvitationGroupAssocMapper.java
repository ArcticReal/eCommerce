package com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyInvitationGroupAssoc.model.PartyInvitationGroupAssoc;

public class PartyInvitationGroupAssocMapper  {


	public static Map<String, Object> map(PartyInvitationGroupAssoc partyinvitationgroupassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyinvitationgroupassoc.getPartyInvitationId() != null ){
			returnVal.put("partyInvitationId",partyinvitationgroupassoc.getPartyInvitationId());
}

		if(partyinvitationgroupassoc.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",partyinvitationgroupassoc.getPartyIdTo());
}

		return returnVal;
}


	public static PartyInvitationGroupAssoc map(Map<String, Object> fields) {

		PartyInvitationGroupAssoc returnVal = new PartyInvitationGroupAssoc();

		if(fields.get("partyInvitationId") != null) {
			returnVal.setPartyInvitationId((String) fields.get("partyInvitationId"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}


		return returnVal;
 } 
	public static PartyInvitationGroupAssoc mapstrstr(Map<String, String> fields) throws Exception {

		PartyInvitationGroupAssoc returnVal = new PartyInvitationGroupAssoc();

		if(fields.get("partyInvitationId") != null) {
			returnVal.setPartyInvitationId((String) fields.get("partyInvitationId"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}


		return returnVal;
 } 
	public static PartyInvitationGroupAssoc map(GenericValue val) {

PartyInvitationGroupAssoc returnVal = new PartyInvitationGroupAssoc();
		returnVal.setPartyInvitationId(val.getString("partyInvitationId"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));


return returnVal;

}

public static PartyInvitationGroupAssoc map(HttpServletRequest request) throws Exception {

		PartyInvitationGroupAssoc returnVal = new PartyInvitationGroupAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyInvitationId")) {
returnVal.setPartyInvitationId(request.getParameter("partyInvitationId"));
}

		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
}
return returnVal;

}
}
