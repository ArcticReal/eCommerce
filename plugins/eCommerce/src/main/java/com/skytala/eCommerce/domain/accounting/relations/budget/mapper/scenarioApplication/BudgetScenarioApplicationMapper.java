package com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenarioApplication;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication.BudgetScenarioApplication;

public class BudgetScenarioApplicationMapper  {


	public static Map<String, Object> map(BudgetScenarioApplication budgetscenarioapplication) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetscenarioapplication.getBudgetScenarioApplicId() != null ){
			returnVal.put("budgetScenarioApplicId",budgetscenarioapplication.getBudgetScenarioApplicId());
}

		if(budgetscenarioapplication.getBudgetScenarioId() != null ){
			returnVal.put("budgetScenarioId",budgetscenarioapplication.getBudgetScenarioId());
}

		if(budgetscenarioapplication.getBudgetId() != null ){
			returnVal.put("budgetId",budgetscenarioapplication.getBudgetId());
}

		if(budgetscenarioapplication.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",budgetscenarioapplication.getBudgetItemSeqId());
}

		if(budgetscenarioapplication.getAmountChange() != null ){
			returnVal.put("amountChange",budgetscenarioapplication.getAmountChange());
}

		if(budgetscenarioapplication.getPercentageChange() != null ){
			returnVal.put("percentageChange",budgetscenarioapplication.getPercentageChange());
}

		return returnVal;
}


	public static BudgetScenarioApplication map(Map<String, Object> fields) {

		BudgetScenarioApplication returnVal = new BudgetScenarioApplication();

		if(fields.get("budgetScenarioApplicId") != null) {
			returnVal.setBudgetScenarioApplicId((String) fields.get("budgetScenarioApplicId"));
}

		if(fields.get("budgetScenarioId") != null) {
			returnVal.setBudgetScenarioId((String) fields.get("budgetScenarioId"));
}

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("amountChange") != null) {
			returnVal.setAmountChange((BigDecimal) fields.get("amountChange"));
}

		if(fields.get("percentageChange") != null) {
			returnVal.setPercentageChange((BigDecimal) fields.get("percentageChange"));
}


		return returnVal;
 } 
	public static BudgetScenarioApplication mapstrstr(Map<String, String> fields) throws Exception {

		BudgetScenarioApplication returnVal = new BudgetScenarioApplication();

		if(fields.get("budgetScenarioApplicId") != null) {
			returnVal.setBudgetScenarioApplicId((String) fields.get("budgetScenarioApplicId"));
}

		if(fields.get("budgetScenarioId") != null) {
			returnVal.setBudgetScenarioId((String) fields.get("budgetScenarioId"));
}

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
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
	public static BudgetScenarioApplication map(GenericValue val) {

BudgetScenarioApplication returnVal = new BudgetScenarioApplication();
		returnVal.setBudgetScenarioApplicId(val.getString("budgetScenarioApplicId"));
		returnVal.setBudgetScenarioId(val.getString("budgetScenarioId"));
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setAmountChange(val.getBigDecimal("amountChange"));
		returnVal.setPercentageChange(val.getBigDecimal("percentageChange"));


return returnVal;

}

public static BudgetScenarioApplication map(HttpServletRequest request) throws Exception {

		BudgetScenarioApplication returnVal = new BudgetScenarioApplication();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetScenarioApplicId")) {
returnVal.setBudgetScenarioApplicId(request.getParameter("budgetScenarioApplicId"));
}

		if(paramMap.containsKey("budgetScenarioId"))  {
returnVal.setBudgetScenarioId(request.getParameter("budgetScenarioId"));
}
		if(paramMap.containsKey("budgetId"))  {
returnVal.setBudgetId(request.getParameter("budgetId"));
}
		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
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
