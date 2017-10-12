package com.skytala.eCommerce.domain.accounting.relations.budgetStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.model.BudgetStatus;

public class BudgetStatusMapper  {


	public static Map<String, Object> map(BudgetStatus budgetstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetstatus.getBudgetId() != null ){
			returnVal.put("budgetId",budgetstatus.getBudgetId());
}

		if(budgetstatus.getStatusId() != null ){
			returnVal.put("statusId",budgetstatus.getStatusId());
}

		if(budgetstatus.getStatusDate() != null ){
			returnVal.put("statusDate",budgetstatus.getStatusDate());
}

		if(budgetstatus.getComments() != null ){
			returnVal.put("comments",budgetstatus.getComments());
}

		if(budgetstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",budgetstatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static BudgetStatus map(Map<String, Object> fields) {

		BudgetStatus returnVal = new BudgetStatus();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static BudgetStatus mapstrstr(Map<String, String> fields) throws Exception {

		BudgetStatus returnVal = new BudgetStatus();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static BudgetStatus map(GenericValue val) {

BudgetStatus returnVal = new BudgetStatus();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static BudgetStatus map(HttpServletRequest request) throws Exception {

		BudgetStatus returnVal = new BudgetStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
