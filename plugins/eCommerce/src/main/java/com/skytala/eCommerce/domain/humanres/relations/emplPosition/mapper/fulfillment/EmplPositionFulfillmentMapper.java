package com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.fulfillment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.fulfillment.EmplPositionFulfillment;

public class EmplPositionFulfillmentMapper  {


	public static Map<String, Object> map(EmplPositionFulfillment emplpositionfulfillment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositionfulfillment.getEmplPositionId() != null ){
			returnVal.put("emplPositionId",emplpositionfulfillment.getEmplPositionId());
}

		if(emplpositionfulfillment.getPartyId() != null ){
			returnVal.put("partyId",emplpositionfulfillment.getPartyId());
}

		if(emplpositionfulfillment.getFromDate() != null ){
			returnVal.put("fromDate",emplpositionfulfillment.getFromDate());
}

		if(emplpositionfulfillment.getThruDate() != null ){
			returnVal.put("thruDate",emplpositionfulfillment.getThruDate());
}

		if(emplpositionfulfillment.getComments() != null ){
			returnVal.put("comments",emplpositionfulfillment.getComments());
}

		return returnVal;
}


	public static EmplPositionFulfillment map(Map<String, Object> fields) {

		EmplPositionFulfillment returnVal = new EmplPositionFulfillment();

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static EmplPositionFulfillment mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionFulfillment returnVal = new EmplPositionFulfillment();

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
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

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static EmplPositionFulfillment map(GenericValue val) {

EmplPositionFulfillment returnVal = new EmplPositionFulfillment();
		returnVal.setEmplPositionId(val.getString("emplPositionId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static EmplPositionFulfillment map(HttpServletRequest request) throws Exception {

		EmplPositionFulfillment returnVal = new EmplPositionFulfillment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionId")) {
returnVal.setEmplPositionId(request.getParameter("emplPositionId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
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
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
