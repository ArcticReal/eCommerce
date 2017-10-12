package com.skytala.eCommerce.domain.accounting.relations.budgetRole.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetRole.model.BudgetRole;

public class BudgetRoleMapper  {


	public static Map<String, Object> map(BudgetRole budgetrole) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetrole.getBudgetId() != null ){
			returnVal.put("budgetId",budgetrole.getBudgetId());
}

		if(budgetrole.getPartyId() != null ){
			returnVal.put("partyId",budgetrole.getPartyId());
}

		if(budgetrole.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",budgetrole.getRoleTypeId());
}

		return returnVal;
}


	public static BudgetRole map(Map<String, Object> fields) {

		BudgetRole returnVal = new BudgetRole();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static BudgetRole mapstrstr(Map<String, String> fields) throws Exception {

		BudgetRole returnVal = new BudgetRole();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}


		return returnVal;
 } 
	public static BudgetRole map(GenericValue val) {

BudgetRole returnVal = new BudgetRole();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));


return returnVal;

}

public static BudgetRole map(HttpServletRequest request) throws Exception {

		BudgetRole returnVal = new BudgetRole();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
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
