package com.skytala.eCommerce.domain.humanres.relations.performanceNote.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;

public class PerformanceNoteMapper  {


	public static Map<String, Object> map(PerformanceNote performancenote) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(performancenote.getPartyId() != null ){
			returnVal.put("partyId",performancenote.getPartyId());
}

		if(performancenote.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",performancenote.getRoleTypeId());
}

		if(performancenote.getFromDate() != null ){
			returnVal.put("fromDate",performancenote.getFromDate());
}

		if(performancenote.getThruDate() != null ){
			returnVal.put("thruDate",performancenote.getThruDate());
}

		if(performancenote.getCommunicationDate() != null ){
			returnVal.put("communicationDate",performancenote.getCommunicationDate());
}

		if(performancenote.getComments() != null ){
			returnVal.put("comments",performancenote.getComments());
}

		return returnVal;
}


	public static PerformanceNote map(Map<String, Object> fields) {

		PerformanceNote returnVal = new PerformanceNote();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("communicationDate") != null) {
			returnVal.setCommunicationDate((Timestamp) fields.get("communicationDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PerformanceNote mapstrstr(Map<String, String> fields) throws Exception {

		PerformanceNote returnVal = new PerformanceNote();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
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

		if(fields.get("communicationDate") != null) {
String buf = fields.get("communicationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCommunicationDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static PerformanceNote map(GenericValue val) {

PerformanceNote returnVal = new PerformanceNote();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setCommunicationDate(val.getTimestamp("communicationDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static PerformanceNote map(HttpServletRequest request) throws Exception {

		PerformanceNote returnVal = new PerformanceNote();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
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
		if(paramMap.containsKey("communicationDate"))  {
String buf = request.getParameter("communicationDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCommunicationDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
