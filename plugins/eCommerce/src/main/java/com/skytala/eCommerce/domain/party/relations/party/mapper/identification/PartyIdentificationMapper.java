package com.skytala.eCommerce.domain.party.relations.party.mapper.identification;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.identification.PartyIdentification;

public class PartyIdentificationMapper  {


	public static Map<String, Object> map(PartyIdentification partyidentification) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyidentification.getPartyId() != null ){
			returnVal.put("partyId",partyidentification.getPartyId());
}

		if(partyidentification.getPartyIdentificationTypeId() != null ){
			returnVal.put("partyIdentificationTypeId",partyidentification.getPartyIdentificationTypeId());
}

		if(partyidentification.getIdValue() != null ){
			returnVal.put("idValue",partyidentification.getIdValue());
}

		return returnVal;
}


	public static PartyIdentification map(Map<String, Object> fields) {

		PartyIdentification returnVal = new PartyIdentification();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("partyIdentificationTypeId") != null) {
			returnVal.setPartyIdentificationTypeId((String) fields.get("partyIdentificationTypeId"));
}

		if(fields.get("idValue") != null) {
			returnVal.setIdValue((String) fields.get("idValue"));
}


		return returnVal;
 } 
	public static PartyIdentification mapstrstr(Map<String, String> fields) throws Exception {

		PartyIdentification returnVal = new PartyIdentification();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("partyIdentificationTypeId") != null) {
			returnVal.setPartyIdentificationTypeId((String) fields.get("partyIdentificationTypeId"));
}

		if(fields.get("idValue") != null) {
			returnVal.setIdValue((String) fields.get("idValue"));
}


		return returnVal;
 } 
	public static PartyIdentification map(GenericValue val) {

PartyIdentification returnVal = new PartyIdentification();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setPartyIdentificationTypeId(val.getString("partyIdentificationTypeId"));
		returnVal.setIdValue(val.getString("idValue"));


return returnVal;

}

public static PartyIdentification map(HttpServletRequest request) throws Exception {

		PartyIdentification returnVal = new PartyIdentification();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("partyIdentificationTypeId"))  {
returnVal.setPartyIdentificationTypeId(request.getParameter("partyIdentificationTypeId"));
}
		if(paramMap.containsKey("idValue"))  {
returnVal.setIdValue(request.getParameter("idValue"));
}
return returnVal;

}
}
