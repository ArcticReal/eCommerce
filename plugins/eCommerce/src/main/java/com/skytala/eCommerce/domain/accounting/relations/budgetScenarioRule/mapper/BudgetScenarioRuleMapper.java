package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.model.BudgetScenarioRule;

public class BudgetScenarioRuleMapper  {


	public static Map<String, Object> map(BudgetScenarioRule budgetscenariorule) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetscenariorule.getBudgetScenarioId() != null ){
			returnVal.put("budgetScenarioId",budgetscenariorule.getBudgetScenarioId());
}

		if(budgetscenariorule.getBudgetItemTypeId() != null ){
			returnVal.put("budgetItemTypeId",budgetscenariorule.getBudgetItemTypeId());
}

		if(budgetscenariorule.getAmountChange() != null ){
			returnVal.put("amountChange",budgetscenariorule.getAmountChange());
}

		if(budgetscenariorule.getPercentageChange() != null ){
			returnVal.put("percentageChange",budgetscenariorule.getPercentageChange());
}

		return returnVal;
}


	public static BudgetScenarioRule map(Map<String, Object> fields) {

		BudgetScenarioRule returnVal = new BudgetScenarioRule();

		if(fields.get("budgetScenarioId") != null) {
			returnVal.setBudgetScenarioId((String) fields.get("budgetScenarioId"));
}

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("amountChange") != null) {
			returnVal.setAmountChange((BigDecimal) fields.get("amountChange"));
}

		if(fields.get("percentageChange") != null) {
			returnVal.setPercentageChange((BigDecimal) fields.get("percentageChange"));
}


		return returnVal;
 } 
	public static BudgetScenarioRule mapstrstr(Map<String, String> fields) throws Exception {

		BudgetScenarioRule returnVal = new BudgetScenarioRule();

		if(fields.get("budgetScenarioId") != null) {
			returnVal.setBudgetScenarioId((String) fields.get("budgetScenarioId"));
}

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("amountChange") != null) {
String buf;
buf = fields.get("amountChange");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountChange(bd);
}

		if(fields.get("percentageChange") != null) {
String buf;
buf = fields.get("percentageChange");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentageChange(bd);
}


		return returnVal;
 } 
	public static BudgetScenarioRule map(GenericValue val) {

BudgetScenarioRule returnVal = new BudgetScenarioRule();
		returnVal.setBudgetScenarioId(val.getString("budgetScenarioId"));
		returnVal.setBudgetItemTypeId(val.getString("budgetItemTypeId"));
		returnVal.setAmountChange(val.getBigDecimal("amountChange"));
		returnVal.setPercentageChange(val.getBigDecimal("percentageChange"));


return returnVal;

}

public static BudgetScenarioRule map(HttpServletRequest request) throws Exception {

		BudgetScenarioRule returnVal = new BudgetScenarioRule();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetScenarioId")) {
returnVal.setBudgetScenarioId(request.getParameter("budgetScenarioId"));
}

		if(paramMap.containsKey("budgetItemTypeId"))  {
returnVal.setBudgetItemTypeId(request.getParameter("budgetItemTypeId"));
}
		if(paramMap.containsKey("amountChange"))  {
String buf = request.getParameter("amountChange");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmountChange(bd);
}
		if(paramMap.containsKey("percentageChange"))  {
String buf = request.getParameter("percentageChange");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPercentageChange(bd);
}
return returnVal;

}
}
