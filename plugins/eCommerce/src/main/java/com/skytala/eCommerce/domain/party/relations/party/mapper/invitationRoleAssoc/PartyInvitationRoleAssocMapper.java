package com.skytala.eCommerce.domain.party.relations.party.mapper.invitationRoleAssoc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;

public class PartyInvitationRoleAssocMapper  {


	public static Map<String, Object> map(PartyInvitationRoleAssoc partyinvitationroleassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyinvitationroleassoc.getPartyInvitationId() != null ){
			returnVal.put("partyInvitationId",partyinvitationroleassoc.getPartyInvitationId());
}

		if(partyinvitationroleassoc.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",partyinvitationroleassoc.getRoleTypeId());
}

		return returnVal;
}


	public static PartyInvitationRoleAssoc map(Map<String, Object> fields) {

		PartyInvitationRoleAssoc returnVal = new PartyInvitationRoleAssoc();

		if(fields.get("partyInvitationId") != null) {
			returnVal.setPartyInvitationId((String) fields.get("partyInvitationId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static PartyInvitationRoleAssoc mapstrstr(Map<String, String> fields) throws Exception {

		PartyInvitationRoleAssoc returnVal = new PartyInvitationRoleAssoc();

		if(fields.get("partyInvitationId") != null) {
			returnVal.setPartyInvitationId((String) fields.get("partyInvitationId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static PartyInvitationRoleAssoc map(GenericValue val) {

PartyInvitationRoleAssoc returnVal = new PartyInvitationRoleAssoc();
		returnVal.setPartyInvitationId(val.getString("partyInvitationId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static PartyInvitationRoleAssoc map(HttpServletRequest request) throws Exception {

		PartyInvitationRoleAssoc returnVal = new PartyInvitationRoleAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyInvitationId")) {
returnVal.setPartyInvitationId(request.getParameter("partyInvitationId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
