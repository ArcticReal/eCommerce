package com.skytala.eCommerce.domain.party.relations.party.mapper.status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.status.PartyStatus;

public class PartyStatusMapper  {


	public static Map<String, Object> map(PartyStatus partystatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partystatus.getStatusId() != null ){
			returnVal.put("statusId",partystatus.getStatusId());
}

		if(partystatus.getPartyId() != null ){
			returnVal.put("partyId",partystatus.getPartyId());
}

		if(partystatus.getStatusDate() != null ){
			returnVal.put("statusDate",partystatus.getStatusDate());
}

		if(partystatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",partystatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static PartyStatus map(Map<String, Object> fields) {

		PartyStatus returnVal = new PartyStatus();

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static PartyStatus mapstrstr(Map<String, String> fields) throws Exception {

		PartyStatus returnVal = new PartyStatus();

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static PartyStatus map(GenericValue val) {

PartyStatus returnVal = new PartyStatus();
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static PartyStatus map(HttpServletRequest request) throws Exception {

		PartyStatus returnVal = new PartyStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("statusId")) {
returnVal.setStatusId(request.getParameter("statusId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
