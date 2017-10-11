package com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyIcsAvsOverride.model.PartyIcsAvsOverride;

public class PartyIcsAvsOverrideMapper  {


	public static Map<String, Object> map(PartyIcsAvsOverride partyicsavsoverride) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyicsavsoverride.getPartyId() != null ){
			returnVal.put("partyId",partyicsavsoverride.getPartyId());
}

		if(partyicsavsoverride.getAvsDeclineString() != null ){
			returnVal.put("avsDeclineString",partyicsavsoverride.getAvsDeclineString());
}

		return returnVal;
}


	public static PartyIcsAvsOverride map(Map<String, Object> fields) {

		PartyIcsAvsOverride returnVal = new PartyIcsAvsOverride();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("avsDeclineString") != null) {
			returnVal.setAvsDeclineString((String) fields.get("avsDeclineString"));
}


		return returnVal;
 } 
	public static PartyIcsAvsOverride mapstrstr(Map<String, String> fields) throws Exception {

		PartyIcsAvsOverride returnVal = new PartyIcsAvsOverride();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("avsDeclineString") != null) {
			returnVal.setAvsDeclineString((String) fields.get("avsDeclineString"));
}


		return returnVal;
 } 
	public static PartyIcsAvsOverride map(GenericValue val) {

PartyIcsAvsOverride returnVal = new PartyIcsAvsOverride();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setAvsDeclineString(val.getString("avsDeclineString"));


return returnVal;

}

public static PartyIcsAvsOverride map(HttpServletRequest request) throws Exception {

		PartyIcsAvsOverride returnVal = new PartyIcsAvsOverride();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("avsDeclineString"))  {
returnVal.setAvsDeclineString(request.getParameter("avsDeclineString"));
}
return returnVal;

}
}
