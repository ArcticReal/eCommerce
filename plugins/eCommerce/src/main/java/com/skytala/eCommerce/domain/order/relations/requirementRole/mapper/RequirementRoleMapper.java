package com.skytala.eCommerce.domain.order.relations.requirementRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;

public class RequirementRoleMapper  {


	public static Map<String, Object> map(RequirementRole requirementrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementrole.getRequirementId() != null ){
			returnVal.put("requirementId",requirementrole.getRequirementId());
}

		if(requirementrole.getPartyId() != null ){
			returnVal.put("partyId",requirementrole.getPartyId());
}

		if(requirementrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",requirementrole.getRoleTypeId());
}

		if(requirementrole.getFromDate() != null ){
			returnVal.put("fromDate",requirementrole.getFromDate());
}

		if(requirementrole.getThruDate() != null ){
			returnVal.put("thruDate",requirementrole.getThruDate());
}

		return returnVal;
}


	public static RequirementRole map(Map<String, Object> fields) {

		RequirementRole returnVal = new RequirementRole();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

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


		return returnVal;
 } 
	public static RequirementRole mapstrstr(Map<String, String> fields) throws Exception {

		RequirementRole returnVal = new RequirementRole();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

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


		return returnVal;
 } 
	public static RequirementRole map(GenericValue val) {

RequirementRole returnVal = new RequirementRole();
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static RequirementRole map(HttpServletRequest request) throws Exception {

		RequirementRole returnVal = new RequirementRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementId")) {
returnVal.setRequirementId(request.getParameter("requirementId"));
}

		if(paramMap.containsKey("partyId"))  {
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
return returnVal;

}
}
