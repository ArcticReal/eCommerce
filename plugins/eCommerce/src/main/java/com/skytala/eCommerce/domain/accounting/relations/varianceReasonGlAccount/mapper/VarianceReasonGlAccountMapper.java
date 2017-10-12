package com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.varianceReasonGlAccount.model.VarianceReasonGlAccount;

public class VarianceReasonGlAccountMapper  {


	public static Map<String, Object> map(VarianceReasonGlAccount variancereasonglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(variancereasonglaccount.getVarianceReasonId() != null ){
			returnVal.put("varianceReasonId",variancereasonglaccount.getVarianceReasonId());
}

		if(variancereasonglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",variancereasonglaccount.getOrganizationPartyId());
}

		if(variancereasonglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",variancereasonglaccount.getGlAccountId());
}

		return returnVal;
}


	public static VarianceReasonGlAccount map(Map<String, Object> fields) {

		VarianceReasonGlAccount returnVal = new VarianceReasonGlAccount();

		if(fields.get("varianceReasonId") != null) {
			returnVal.setVarianceReasonId((String) fields.get("varianceReasonId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static VarianceReasonGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		VarianceReasonGlAccount returnVal = new VarianceReasonGlAccount();

		if(fields.get("varianceReasonId") != null) {
			returnVal.setVarianceReasonId((String) fields.get("varianceReasonId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static VarianceReasonGlAccount map(GenericValue val) {

VarianceReasonGlAccount returnVal = new VarianceReasonGlAccount();
		returnVal.setVarianceReasonId(val.getString("varianceReasonId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static VarianceReasonGlAccount map(HttpServletRequest request) throws Exception {

		VarianceReasonGlAccount returnVal = new VarianceReasonGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("varianceReasonId")) {
returnVal.setVarianceReasonId(request.getParameter("varianceReasonId"));
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
