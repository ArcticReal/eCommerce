package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.model.SalesOpportunityRole;

public class SalesOpportunityRoleMapper  {


	public static Map<String, Object> map(SalesOpportunityRole salesopportunityrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunityrole.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunityrole.getSalesOpportunityId());
}

		if(salesopportunityrole.getPartyId() != null ){
			returnVal.put("partyId",salesopportunityrole.getPartyId());
}

		if(salesopportunityrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",salesopportunityrole.getRoleTypeId());
}

		return returnVal;
}


	public static SalesOpportunityRole map(Map<String, Object> fields) {

		SalesOpportunityRole returnVal = new SalesOpportunityRole();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static SalesOpportunityRole mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityRole returnVal = new SalesOpportunityRole();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static SalesOpportunityRole map(GenericValue val) {

SalesOpportunityRole returnVal = new SalesOpportunityRole();
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static SalesOpportunityRole map(HttpServletRequest request) throws Exception {

		SalesOpportunityRole returnVal = new SalesOpportunityRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityId")) {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
return returnVal;

}
}
