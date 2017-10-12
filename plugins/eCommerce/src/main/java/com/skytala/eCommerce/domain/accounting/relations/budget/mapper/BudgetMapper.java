package com.skytala.eCommerce.domain.accounting.relations.budget.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.Budget;

public class BudgetMapper  {


	public static Map<String, Object> map(Budget budget) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budget.getBudgetId() != null ){
			returnVal.put("budgetId",budget.getBudgetId());
}

		if(budget.getBudgetTypeId() != null ){
			returnVal.put("budgetTypeId",budget.getBudgetTypeId());
}

		if(budget.getCustomTimePeriodId() != null ){
			returnVal.put("customTimePeriodId",budget.getCustomTimePeriodId());
}

		if(budget.getComments() != null ){
			returnVal.put("comments",budget.getComments());
}

		return returnVal;
}


	public static Budget map(Map<String, Object> fields) {

		Budget returnVal = new Budget();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetTypeId") != null) {
			returnVal.setBudgetTypeId((String) fields.get("budgetTypeId"));
}

		if(fields.get("customTimePeriodId") != null) {
			returnVal.setCustomTimePeriodId((String) fields.get("customTimePeriodId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static Budget mapstrstr(Map<String, String> fields) throws Exception {

		Budget returnVal = new Budget();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetTypeId") != null) {
			returnVal.setBudgetTypeId((String) fields.get("budgetTypeId"));
}

		if(fields.get("customTimePeriodId") != null) {
			returnVal.setCustomTimePeriodId((String) fields.get("customTimePeriodId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static Budget map(GenericValue val) {

Budget returnVal = new Budget();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetTypeId(val.getString("budgetTypeId"));
		returnVal.setCustomTimePeriodId(val.getString("customTimePeriodId"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static Budget map(HttpServletRequest request) throws Exception {

		Budget returnVal = new Budget();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetTypeId"))  {
returnVal.setBudgetTypeId(request.getParameter("budgetTypeId"));
}
		if(paramMap.containsKey("customTimePeriodId"))  {
returnVal.setCustomTimePeriodId(request.getParameter("customTimePeriodId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
