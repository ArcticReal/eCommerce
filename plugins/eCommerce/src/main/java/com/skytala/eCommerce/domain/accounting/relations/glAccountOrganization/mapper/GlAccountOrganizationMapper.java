package com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountOrganization.model.GlAccountOrganization;

public class GlAccountOrganizationMapper  {


	public static Map<String, Object> map(GlAccountOrganization glaccountorganization) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccountorganization.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccountorganization.getGlAccountId());
}

		if(glaccountorganization.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",glaccountorganization.getOrganizationPartyId());
}

		if(glaccountorganization.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",glaccountorganization.getRoleTypeId());
}

		if(glaccountorganization.getFromDate() != null ){
			returnVal.put("fromDate",glaccountorganization.getFromDate());
}

		if(glaccountorganization.getThruDate() != null ){
			returnVal.put("thruDate",glaccountorganization.getThruDate());
}

		return returnVal;
}


	public static GlAccountOrganization map(Map<String, Object> fields) {

		GlAccountOrganization returnVal = new GlAccountOrganization();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
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
	public static GlAccountOrganization mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountOrganization returnVal = new GlAccountOrganization();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
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
	public static GlAccountOrganization map(GenericValue val) {

GlAccountOrganization returnVal = new GlAccountOrganization();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static GlAccountOrganization map(HttpServletRequest request) throws Exception {

		GlAccountOrganization returnVal = new GlAccountOrganization();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
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
