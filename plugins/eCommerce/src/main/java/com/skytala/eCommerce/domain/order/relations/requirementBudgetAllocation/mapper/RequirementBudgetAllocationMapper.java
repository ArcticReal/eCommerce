package com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirementBudgetAllocation.model.RequirementBudgetAllocation;

public class RequirementBudgetAllocationMapper  {


	public static Map<String, Object> map(RequirementBudgetAllocation requirementbudgetallocation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementbudgetallocation.getBudgetId() != null ){
			returnVal.put("budgetId",requirementbudgetallocation.getBudgetId());
}

		if(requirementbudgetallocation.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",requirementbudgetallocation.getBudgetItemSeqId());
}

		if(requirementbudgetallocation.getRequirementId() != null ){
			returnVal.put("requirementId",requirementbudgetallocation.getRequirementId());
}

		if(requirementbudgetallocation.getAmount() != null ){
			returnVal.put("amount",requirementbudgetallocation.getAmount());
}

		return returnVal;
}


	public static RequirementBudgetAllocation map(Map<String, Object> fields) {

		RequirementBudgetAllocation returnVal = new RequirementBudgetAllocation();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}


		return returnVal;
 } 
	public static RequirementBudgetAllocation mapstrstr(Map<String, String> fields) throws Exception {

		RequirementBudgetAllocation returnVal = new RequirementBudgetAllocation();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}


		return returnVal;
 } 
	public static RequirementBudgetAllocation map(GenericValue val) {

RequirementBudgetAllocation returnVal = new RequirementBudgetAllocation();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setAmount(val.getBigDecimal("amount"));


return returnVal;

}

public static RequirementBudgetAllocation map(HttpServletRequest request) throws Exception {

		RequirementBudgetAllocation returnVal = new RequirementBudgetAllocation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
}
		if(paramMap.containsKey("requirementId"))  {
returnVal.setRequirementId(request.getParameter("requirementId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
return returnVal;

}
}
