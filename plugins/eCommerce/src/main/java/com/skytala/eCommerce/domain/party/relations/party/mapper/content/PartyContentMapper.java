package com.skytala.eCommerce.domain.party.relations.party.mapper.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.model.content.PartyContent;

public class PartyContentMapper  {


	public static Map<String, Object> map(PartyContent partycontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partycontent.getPartyId() != null ){
			returnVal.put("partyId",partycontent.getPartyId());
}

		if(partycontent.getContentId() != null ){
			returnVal.put("contentId",partycontent.getContentId());
}

		if(partycontent.getPartyContentTypeId() != null ){
			returnVal.put("partyContentTypeId",partycontent.getPartyContentTypeId());
}

		if(partycontent.getFromDate() != null ){
			returnVal.put("fromDate",partycontent.getFromDate());
}

		if(partycontent.getThruDate() != null ){
			returnVal.put("thruDate",partycontent.getThruDate());
}

		return returnVal;
}


	public static PartyContent map(Map<String, Object> fields) {

		PartyContent returnVal = new PartyContent();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("partyContentTypeId") != null) {
			returnVal.setPartyContentTypeId((String) fields.get("partyContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static PartyContent mapstrstr(Map<String, String> fields) throws Exception {

		PartyContent returnVal = new PartyContent();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("partyContentTypeId") != null) {
			returnVal.setPartyContentTypeId((String) fields.get("partyContentTypeId"));
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
	public static PartyContent map(GenericValue val) {

PartyContent returnVal = new PartyContent();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setPartyContentTypeId(val.getString("partyContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static PartyContent map(HttpServletRequest request) throws Exception {

		PartyContent returnVal = new PartyContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("partyContentTypeId"))  {
returnVal.setPartyContentTypeId(request.getParameter("partyContentTypeId"));
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
