package com.skytala.eCommerce.domain.party.relations.party.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.role.PartyRole;

public class PartyRoleMapper  {


	public static Map<String, Object> map(PartyRole partyrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyrole.getPartyId() != null ){
			returnVal.put("partyId",partyrole.getPartyId());
}

		if(partyrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",partyrole.getRoleTypeId());
}

		return returnVal;
}


	public static PartyRole map(Map<String, Object> fields) {

		PartyRole returnVal = new PartyRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static PartyRole mapstrstr(Map<String, String> fields) throws Exception {

		PartyRole returnVal = new PartyRole();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static PartyRole map(GenericValue val) {

PartyRole returnVal = new PartyRole();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static PartyRole map(HttpServletRequest request) throws Exception {

		PartyRole returnVal = new PartyRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
