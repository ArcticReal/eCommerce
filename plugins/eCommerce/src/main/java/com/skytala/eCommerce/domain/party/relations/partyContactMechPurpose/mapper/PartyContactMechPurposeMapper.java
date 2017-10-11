package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model.PartyContactMechPurpose;

public class PartyContactMechPurposeMapper  {


	public static Map<String, Object> map(PartyContactMechPurpose partycontactmechpurpose) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partycontactmechpurpose.getPartyId() != null ){
			returnVal.put("partyId",partycontactmechpurpose.getPartyId());
}

		if(partycontactmechpurpose.getContactMechId() != null ){
			returnVal.put("contactMechId",partycontactmechpurpose.getContactMechId());
}

		if(partycontactmechpurpose.getContactMechPurposeTypeId() != null ){
			returnVal.put("contactMechPurposeTypeId",partycontactmechpurpose.getContactMechPurposeTypeId());
}

		if(partycontactmechpurpose.getFromDate() != null ){
			returnVal.put("fromDate",partycontactmechpurpose.getFromDate());
}

		if(partycontactmechpurpose.getThruDate() != null ){
			returnVal.put("thruDate",partycontactmechpurpose.getThruDate());
}

		return returnVal;
}


	public static PartyContactMechPurpose map(Map<String, Object> fields) {

		PartyContactMechPurpose returnVal = new PartyContactMechPurpose();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PartyContactMechPurpose mapstrstr(Map<String, String> fields) throws Exception {

		PartyContactMechPurpose returnVal = new PartyContactMechPurpose();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("contactMechPurposeTypeId") != null) {
			returnVal.setContactMechPurposeTypeId((String) fields.get("contactMechPurposeTypeId"));
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
	public static PartyContactMechPurpose map(GenericValue val) {

PartyContactMechPurpose returnVal = new PartyContactMechPurpose();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setContactMechPurposeTypeId(val.getString("contactMechPurposeTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PartyContactMechPurpose map(HttpServletRequest request) throws Exception {

		PartyContactMechPurpose returnVal = new PartyContactMechPurpose();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("contactMechPurposeTypeId"))  {
returnVal.setContactMechPurposeTypeId(request.getParameter("contactMechPurposeTypeId"));
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
