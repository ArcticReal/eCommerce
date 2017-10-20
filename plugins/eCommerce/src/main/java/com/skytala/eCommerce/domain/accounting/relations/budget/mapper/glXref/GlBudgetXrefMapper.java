package com.skytala.eCommerce.domain.accounting.relations.budget.mapper.glXref;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref.GlBudgetXref;

public class GlBudgetXrefMapper  {


	public static Map<String, Object> map(GlBudgetXref glbudgetxref) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glbudgetxref.getGlAccountId() != null ){
			returnVal.put("glAccountId",glbudgetxref.getGlAccountId());
}

		if(glbudgetxref.getBudgetItemTypeId() != null ){
			returnVal.put("budgetItemTypeId",glbudgetxref.getBudgetItemTypeId());
}

		if(glbudgetxref.getFromDate() != null ){
			returnVal.put("fromDate",glbudgetxref.getFromDate());
}

		if(glbudgetxref.getThruDate() != null ){
			returnVal.put("thruDate",glbudgetxref.getThruDate());
}

		if(glbudgetxref.getAllocationPercentage() != null ){
			returnVal.put("allocationPercentage",glbudgetxref.getAllocationPercentage());
}

		return returnVal;
}


	public static GlBudgetXref map(Map<String, Object> fields) {

		GlBudgetXref returnVal = new GlBudgetXref();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("allocationPercentage") != null) {
			returnVal.setAllocationPercentage((BigDecimal) fields.get("allocationPercentage"));
}


		return returnVal;
 } 
	public static GlBudgetXref mapstrstr(Map<String, String> fields) throws Exception {

		GlBudgetXref returnVal = new GlBudgetXref();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("allocationPercentage") != null) {
String buf;
buf = fields.get("allocationPercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAllocationPercentage(bd);
}


		return returnVal;
 } 
	public static GlBudgetXref map(GenericValue val) {

GlBudgetXref returnVal = new GlBudgetXref();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setBudgetItemTypeId(val.getString("budgetItemTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setAllocationPercentage(val.getBigDecimal("allocationPercentage"));


return returnVal;

}

public static GlBudgetXref map(HttpServletRequest request) throws Exception {

		GlBudgetXref returnVal = new GlBudgetXref();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}

		if(paramMap.containsKey("budgetItemTypeId"))  {
returnVal.setBudgetItemTypeId(request.getParameter("budgetItemTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("allocationPercentage"))  {
String buf = request.getParameter("allocationPercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAllocationPercentage(bd);
}
return returnVal;

}
}
