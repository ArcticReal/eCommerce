package com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.model.GlAccountTypeDefault;

public class GlAccountTypeDefaultMapper  {


	public static Map<String, Object> map(GlAccountTypeDefault glaccounttypedefault) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccounttypedefault.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",glaccounttypedefault.getGlAccountTypeId());
}

		if(glaccounttypedefault.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",glaccounttypedefault.getOrganizationPartyId());
}

		if(glaccounttypedefault.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccounttypedefault.getGlAccountId());
}

		return returnVal;
}


	public static GlAccountTypeDefault map(Map<String, Object> fields) {

		GlAccountTypeDefault returnVal = new GlAccountTypeDefault();

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static GlAccountTypeDefault mapstrstr(Map<String, String> fields) throws Exception {

		GlAccountTypeDefault returnVal = new GlAccountTypeDefault();

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static GlAccountTypeDefault map(GenericValue val) {

GlAccountTypeDefault returnVal = new GlAccountTypeDefault();
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static GlAccountTypeDefault map(HttpServletRequest request) throws Exception {

		GlAccountTypeDefault returnVal = new GlAccountTypeDefault();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountTypeId")) {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}

		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
return returnVal;

}
}
