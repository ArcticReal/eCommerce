package com.skytala.eCommerce.domain.humanres.relations.employment.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.employment.model.Employment;

public class EmploymentMapper  {


	public static Map<String, Object> map(Employment employment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(employment.getRoleTypeIdFrom() != null ){
			returnVal.put("roleTypeIdFrom",employment.getRoleTypeIdFrom());
}

		if(employment.getRoleTypeIdTo() != null ){
			returnVal.put("roleTypeIdTo",employment.getRoleTypeIdTo());
}

		if(employment.getPartyIdFrom() != null ){
			returnVal.put("partyIdFrom",employment.getPartyIdFrom());
}

		if(employment.getPartyIdTo() != null ){
			returnVal.put("partyIdTo",employment.getPartyIdTo());
}

		if(employment.getFromDate() != null ){
			returnVal.put("fromDate",employment.getFromDate());
}

		if(employment.getThruDate() != null ){
			returnVal.put("thruDate",employment.getThruDate());
}

		if(employment.getTerminationReasonId() != null ){
			returnVal.put("terminationReasonId",employment.getTerminationReasonId());
}

		if(employment.getTerminationTypeId() != null ){
			returnVal.put("terminationTypeId",employment.getTerminationTypeId());
}

		return returnVal;
}


	public static Employment map(Map<String, Object> fields) {

		Employment returnVal = new Employment();

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("terminationReasonId") != null) {
			returnVal.setTerminationReasonId((String) fields.get("terminationReasonId"));
}

		if(fields.get("terminationTypeId") != null) {
			returnVal.setTerminationTypeId((String) fields.get("terminationTypeId"));
}


		return returnVal;
 } 
	public static Employment mapstrstr(Map<String, String> fields) throws Exception {

		Employment returnVal = new Employment();

		if(fields.get("roleTypeIdFrom") != null) {
			returnVal.setRoleTypeIdFrom((String) fields.get("roleTypeIdFrom"));
}

		if(fields.get("roleTypeIdTo") != null) {
			returnVal.setRoleTypeIdTo((String) fields.get("roleTypeIdTo"));
}

		if(fields.get("partyIdFrom") != null) {
			returnVal.setPartyIdFrom((String) fields.get("partyIdFrom"));
}

		if(fields.get("partyIdTo") != null) {
			returnVal.setPartyIdTo((String) fields.get("partyIdTo"));
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

		if(fields.get("terminationReasonId") != null) {
			returnVal.setTerminationReasonId((String) fields.get("terminationReasonId"));
}

		if(fields.get("terminationTypeId") != null) {
			returnVal.setTerminationTypeId((String) fields.get("terminationTypeId"));
}


		return returnVal;
 } 
	public static Employment map(GenericValue val) {

Employment returnVal = new Employment();
		returnVal.setRoleTypeIdFrom(val.getString("roleTypeIdFrom"));
		returnVal.setRoleTypeIdTo(val.getString("roleTypeIdTo"));
		returnVal.setPartyIdFrom(val.getString("partyIdFrom"));
		returnVal.setPartyIdTo(val.getString("partyIdTo"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setTerminationReasonId(val.getString("terminationReasonId"));
		returnVal.setTerminationTypeId(val.getString("terminationTypeId"));


return returnVal;

}

public static Employment map(HttpServletRequest request) throws Exception {

		Employment returnVal = new Employment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("roleTypeIdFrom")) {
returnVal.setRoleTypeIdFrom(request.getParameter("roleTypeIdFrom"));
}

		if(paramMap.containsKey("roleTypeIdTo"))  {
returnVal.setRoleTypeIdTo(request.getParameter("roleTypeIdTo"));
}
		if(paramMap.containsKey("partyIdFrom"))  {
returnVal.setPartyIdFrom(request.getParameter("partyIdFrom"));
}
		if(paramMap.containsKey("partyIdTo"))  {
returnVal.setPartyIdTo(request.getParameter("partyIdTo"));
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
		if(paramMap.containsKey("terminationReasonId"))  {
returnVal.setTerminationReasonId(request.getParameter("terminationReasonId"));
}
		if(paramMap.containsKey("terminationTypeId"))  {
returnVal.setTerminationTypeId(request.getParameter("terminationTypeId"));
}
return returnVal;

}
}
