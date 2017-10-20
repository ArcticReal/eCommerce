package com.skytala.eCommerce.domain.accounting.relations.glAccount.mapper.role;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.role.GlAccountRole;

public class GlAccountRoleMapper  {


	public static Map<String, Object> map(GlAccountRole glaccountrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountrole.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccountrole.getGlAccountId());
}

		if(glaccountrole.getPartyId() != null ){
			returnVal.put("partyId",glaccountrole.getPartyId());
}

		if(glaccountrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",glaccountrole.getRoleTypeId());
}

		if(glaccountrole.getFromDate() != null ){
			returnVal.put("fromDate",glaccountrole.getFromDate());
}

		if(glaccountrole.getThruDate() != null ){
			returnVal.put("thruDate",glaccountrole.getThruDate());
}

		return returnVal;
}


	public static GlAccountRole map(Map<String, Object> fields) {

		GlAccountRole returnVal = new GlAccountRole();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
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
	public static GlAccountRole mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountRole returnVal = new GlAccountRole();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
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
	public static GlAccountRole map(GenericValue val) {

GlAccountRole returnVal = new GlAccountRole();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static GlAccountRole map(HttpServletRequest request) throws Exception {

		GlAccountRole returnVal = new GlAccountRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
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
