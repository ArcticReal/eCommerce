package com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.model.EmplPositionResponsibility;

public class EmplPositionResponsibilityMapper  {


	public static Map<String, Object> map(EmplPositionResponsibility emplpositionresponsibility) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(emplpositionresponsibility.getEmplPositionId() != null ){
			returnVal.put("emplPositionId",emplpositionresponsibility.getEmplPositionId());
}

		if(emplpositionresponsibility.getResponsibilityTypeId() != null ){
			returnVal.put("responsibilityTypeId",emplpositionresponsibility.getResponsibilityTypeId());
}

		if(emplpositionresponsibility.getFromDate() != null ){
			returnVal.put("fromDate",emplpositionresponsibility.getFromDate());
}

		if(emplpositionresponsibility.getThruDate() != null ){
			returnVal.put("thruDate",emplpositionresponsibility.getThruDate());
}

		if(emplpositionresponsibility.getComments() != null ){
			returnVal.put("comments",emplpositionresponsibility.getComments());
}

		return returnVal;
}


	public static EmplPositionResponsibility map(Map<String, Object> fields) {

		EmplPositionResponsibility returnVal = new EmplPositionResponsibility();

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("responsibilityTypeId") != null) {
			returnVal.setResponsibilityTypeId((String) fields.get("responsibilityTypeId"));
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
	public static EmplPositionResponsibility mapstrstr(Map<String, String> fields) throws Exception {

		EmplPositionResponsibility returnVal = new EmplPositionResponsibility();

		if(fields.get("emplPositionId") != null) {
			returnVal.setEmplPositionId((String) fields.get("emplPositionId"));
}

		if(fields.get("responsibilityTypeId") != null) {
			returnVal.setResponsibilityTypeId((String) fields.get("responsibilityTypeId"));
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
	public static EmplPositionResponsibility map(GenericValue val) {

EmplPositionResponsibility returnVal = new EmplPositionResponsibility();
		returnVal.setEmplPositionId(val.getString("emplPositionId"));
		returnVal.setResponsibilityTypeId(val.getString("responsibilityTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static EmplPositionResponsibility map(HttpServletRequest request) throws Exception {

		EmplPositionResponsibility returnVal = new EmplPositionResponsibility();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionId")) {
returnVal.setEmplPositionId(request.getParameter("emplPositionId"));
}

		if(paramMap.containsKey("responsibilityTypeId"))  {
returnVal.setResponsibilityTypeId(request.getParameter("responsibilityTypeId"));
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
