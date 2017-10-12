package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model.BudgetRevisionImpact;

public class BudgetRevisionImpactMapper  {


	public static Map<String, Object> map(BudgetRevisionImpact budgetrevisionimpact) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetrevisionimpact.getBudgetId() != null ){
			returnVal.put("budgetId",budgetrevisionimpact.getBudgetId());
}

		if(budgetrevisionimpact.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",budgetrevisionimpact.getBudgetItemSeqId());
}

		if(budgetrevisionimpact.getRevisionSeqId() != null ){
			returnVal.put("revisionSeqId",budgetrevisionimpact.getRevisionSeqId());
}

		if(budgetrevisionimpact.getRevisedAmount() != null ){
			returnVal.put("revisedAmount",budgetrevisionimpact.getRevisedAmount());
}

		if(budgetrevisionimpact.getAddDeleteFlag() != null ){
			returnVal.put("addDeleteFlag",budgetrevisionimpact.getAddDeleteFlag());
}

		if(budgetrevisionimpact.getRevisionReason() != null ){
			returnVal.put("revisionReason",budgetrevisionimpact.getRevisionReason());
}

		return returnVal;
}


	public static BudgetRevisionImpact map(Map<String, Object> fields) {

		BudgetRevisionImpact returnVal = new BudgetRevisionImpact();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("revisionSeqId") != null) {
			returnVal.setRevisionSeqId((String) fields.get("revisionSeqId"));
}

		if(fields.get("revisedAmount") != null) {
			returnVal.setRevisedAmount((BigDecimal) fields.get("revisedAmount"));
}

		if(fields.get("addDeleteFlag") != null) {
			returnVal.setAddDeleteFlag((boolean) fields.get("addDeleteFlag"));
}

		if(fields.get("revisionReason") != null) {
			returnVal.setRevisionReason((String) fields.get("revisionReason"));
}


		return returnVal;
 } 
	public static BudgetRevisionImpact mapstrstr(Map<String, String> fields) throws Exception {

		BudgetRevisionImpact returnVal = new BudgetRevisionImpact();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("revisionSeqId") != null) {
			returnVal.setRevisionSeqId((String) fields.get("revisionSeqId"));
}

		if(fields.get("revisedAmount") != null) {
String buf;
buf = fields.get("revisedAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRevisedAmount(bd);
}

		if(fields.get("addDeleteFlag") != null) {
String buf;
buf = fields.get("addDeleteFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAddDeleteFlag(ibuf);
}

		if(fields.get("revisionReason") != null) {
			returnVal.setRevisionReason((String) fields.get("revisionReason"));
}


		return returnVal;
 } 
	public static BudgetRevisionImpact map(GenericValue val) {

BudgetRevisionImpact returnVal = new BudgetRevisionImpact();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setRevisionSeqId(val.getString("revisionSeqId"));
		returnVal.setRevisedAmount(val.getBigDecimal("revisedAmount"));
		returnVal.setAddDeleteFlag(val.getBoolean("addDeleteFlag"));
		returnVal.setRevisionReason(val.getString("revisionReason"));


return returnVal;

}

public static BudgetRevisionImpact map(HttpServletRequest request) throws Exception {

		BudgetRevisionImpact returnVal = new BudgetRevisionImpact();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
}
		if(paramMap.containsKey("revisionSeqId"))  {
returnVal.setRevisionSeqId(request.getParameter("revisionSeqId"));
}
		if(paramMap.containsKey("revisedAmount"))  {
String buf = request.getParameter("revisedAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setRevisedAmount(bd);
}
		if(paramMap.containsKey("addDeleteFlag"))  {
String buf = request.getParameter("addDeleteFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAddDeleteFlag(ibuf);
}
		if(paramMap.containsKey("revisionReason"))  {
returnVal.setRevisionReason(request.getParameter("revisionReason"));
}
return returnVal;

}
}
