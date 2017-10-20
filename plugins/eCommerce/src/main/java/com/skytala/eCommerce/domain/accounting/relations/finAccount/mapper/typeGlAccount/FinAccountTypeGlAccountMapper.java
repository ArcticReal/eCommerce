package com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.typeGlAccount;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeGlAccount.FinAccountTypeGlAccount;

public class FinAccountTypeGlAccountMapper  {


	public static Map<String, Object> map(FinAccountTypeGlAccount finaccounttypeglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(finaccounttypeglaccount.getFinAccountTypeId() != null ){
			returnVal.put("finAccountTypeId",finaccounttypeglaccount.getFinAccountTypeId());
}

		if(finaccounttypeglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",finaccounttypeglaccount.getOrganizationPartyId());
}

		if(finaccounttypeglaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",finaccounttypeglaccount.getGlAccountId());
}

		return returnVal;
}


	public static FinAccountTypeGlAccount map(Map<String, Object> fields) {

		FinAccountTypeGlAccount returnVal = new FinAccountTypeGlAccount();

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static FinAccountTypeGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		FinAccountTypeGlAccount returnVal = new FinAccountTypeGlAccount();

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}


		return returnVal;
 } 
	public static FinAccountTypeGlAccount map(GenericValue val) {

FinAccountTypeGlAccount returnVal = new FinAccountTypeGlAccount();
		returnVal.setFinAccountTypeId(val.getString("finAccountTypeId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setGlAccountId(val.getString("glAccountId"));


return returnVal;

}

public static FinAccountTypeGlAccount map(HttpServletRequest request) throws Exception {

		FinAccountTypeGlAccount returnVal = new FinAccountTypeGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("finAccountTypeId")) {
returnVal.setFinAccountTypeId(request.getParameter("finAccountTypeId"));
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
