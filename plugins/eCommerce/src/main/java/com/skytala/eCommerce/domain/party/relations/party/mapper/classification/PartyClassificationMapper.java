package com.skytala.eCommerce.domain.party.relations.party.mapper.classification;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;

public class PartyClassificationMapper  {


	public static Map<String, Object> map(PartyClassification partyclassification) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyclassification.getPartyId() != null ){
			returnVal.put("partyId",partyclassification.getPartyId());
}

		if(partyclassification.getPartyClassificationGroupId() != null ){
			returnVal.put("partyClassificationGroupId",partyclassification.getPartyClassificationGroupId());
}

		if(partyclassification.getFromDate() != null ){
			returnVal.put("fromDate",partyclassification.getFromDate());
}

		if(partyclassification.getThruDate() != null ){
			returnVal.put("thruDate",partyclassification.getThruDate());
}

		return returnVal;
}


	public static PartyClassification map(Map<String, Object> fields) {

		PartyClassification returnVal = new PartyClassification();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PartyClassification mapstrstr(Map<String, String> fields) throws Exception {

		PartyClassification returnVal = new PartyClassification();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("partyClassificationGroupId") != null) {
			returnVal.setPartyClassificationGroupId((String) fields.get("partyClassificationGroupId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static PartyClassification map(GenericValue val) {

PartyClassification returnVal = new PartyClassification();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setPartyClassificationGroupId(val.getString("partyClassificationGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PartyClassification map(HttpServletRequest request) throws Exception {

		PartyClassification returnVal = new PartyClassification();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("partyClassificationGroupId"))  {
returnVal.setPartyClassificationGroupId(request.getParameter("partyClassificationGroupId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
