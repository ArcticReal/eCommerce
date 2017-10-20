package com.skytala.eCommerce.domain.accounting.relations.budget.mapper.item;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;

public class BudgetItemMapper  {


	public static Map<String, Object> map(BudgetItem budgetitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetitem.getBudgetId() != null ){
			returnVal.put("budgetId",budgetitem.getBudgetId());
}

		if(budgetitem.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",budgetitem.getBudgetItemSeqId());
}

		if(budgetitem.getBudgetItemTypeId() != null ){
			returnVal.put("budgetItemTypeId",budgetitem.getBudgetItemTypeId());
}

		if(budgetitem.getAmount() != null ){
			returnVal.put("amount",budgetitem.getAmount());
}

		if(budgetitem.getPurpose() != null ){
			returnVal.put("purpose",budgetitem.getPurpose());
}

		if(budgetitem.getJustification() != null ){
			returnVal.put("justification",budgetitem.getJustification());
}

		return returnVal;
}


	public static BudgetItem map(Map<String, Object> fields) {

		BudgetItem returnVal = new BudgetItem();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("purpose") != null) {
			returnVal.setPurpose((String) fields.get("purpose"));
}

		if(fields.get("justification") != null) {
			returnVal.setJustification((String) fields.get("justification"));
}


		return returnVal;
 } 
	public static BudgetItem mapstrstr(Map<String, String> fields) throws Exception {

		BudgetItem returnVal = new BudgetItem();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("purpose") != null) {
			returnVal.setPurpose((String) fields.get("purpose"));
}

		if(fields.get("justification") != null) {
			returnVal.setJustification((String) fields.get("justification"));
}


		return returnVal;
 } 
	public static BudgetItem map(GenericValue val) {

BudgetItem returnVal = new BudgetItem();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setBudgetItemTypeId(val.getString("budgetItemTypeId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setPurpose(val.getString("purpose"));
		returnVal.setJustification(val.getString("justification"));


return returnVal;

}

public static BudgetItem map(HttpServletRequest request) throws Exception {

		BudgetItem returnVal = new BudgetItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
}
		if(paramMap.containsKey("budgetItemTypeId"))  {
returnVal.setBudgetItemTypeId(request.getParameter("budgetItemTypeId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("purpose"))  {
returnVal.setPurpose(request.getParameter("purpose"));
}
		if(paramMap.containsKey("justification"))  {
returnVal.setJustification(request.getParameter("justification"));
}
return returnVal;

}
}
