package com.skytala.eCommerce.domain.humanres.relations.validResponsibility.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.validResponsibility.model.ValidResponsibility;

public class ValidResponsibilityMapper  {


	public static Map<String, Object> map(ValidResponsibility validresponsibility) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(validresponsibility.getEmplPositionTypeId() != null ){
			returnVal.put("emplPositionTypeId",validresponsibility.getEmplPositionTypeId());
}

		if(validresponsibility.getResponsibilityTypeId() != null ){
			returnVal.put("responsibilityTypeId",validresponsibility.getResponsibilityTypeId());
}

		if(validresponsibility.getFromDate() != null ){
			returnVal.put("fromDate",validresponsibility.getFromDate());
}

		if(validresponsibility.getThruDate() != null ){
			returnVal.put("thruDate",validresponsibility.getThruDate());
}

		if(validresponsibility.getComments() != null ){
			returnVal.put("comments",validresponsibility.getComments());
}

		return returnVal;
}


	public static ValidResponsibility map(Map<String, Object> fields) {

		ValidResponsibility returnVal = new ValidResponsibility();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
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
	public static ValidResponsibility mapstrstr(Map<String, String> fields) throws Exception {

		ValidResponsibility returnVal = new ValidResponsibility();

		if(fields.get("emplPositionTypeId") != null) {
			returnVal.setEmplPositionTypeId((String) fields.get("emplPositionTypeId"));
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
	public static ValidResponsibility map(GenericValue val) {

ValidResponsibility returnVal = new ValidResponsibility();
		returnVal.setEmplPositionTypeId(val.getString("emplPositionTypeId"));
		returnVal.setResponsibilityTypeId(val.getString("responsibilityTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static ValidResponsibility map(HttpServletRequest request) throws Exception {

		ValidResponsibility returnVal = new ValidResponsibility();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("emplPositionTypeId")) {
returnVal.setEmplPositionTypeId(request.getParameter("emplPositionTypeId"));
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
