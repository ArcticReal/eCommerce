package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;

public class PartyGlAccountMapper  {


	public static Map<String, Object> map(PartyGlAccount partyglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(partyglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",partyglaccount.getOrganizationPartyId());
}

		if(partyglaccount.getPartyId() != null ){
			returnVal.put("partyId",partyglaccount.getPartyId());
}

		if(partyglaccount.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",partyglaccount.getRoleTypeId());
}

		if(partyglaccount.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",partyglaccount.getGlAccountTypeId());
}

		if(partyglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",partyglaccount.getGlAccountId());
}

		return returnVal;
}


	public static PartyGlAccount map(Map<String, Object> fields) {

		PartyGlAccount returnVal = new PartyGlAccount();

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static PartyGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		PartyGlAccount returnVal = new PartyGlAccount();

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static PartyGlAccount map(GenericValue val) {

PartyGlAccount returnVal = new PartyGlAccount();
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static PartyGlAccount map(HttpServletRequest request) throws Exception {

		PartyGlAccount returnVal = new PartyGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("organizationPartyId")) {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("glAccountTypeId"))  {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
