package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;

public class BudgetItemAttributeMapper  {


	public static Map<String, Object> map(BudgetItemAttribute budgetitemattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetitemattribute.getBudgetId() != null ){
			returnVal.put("budgetId",budgetitemattribute.getBudgetId());
}

		if(budgetitemattribute.getBudgetItemSeqId() != null ){
			returnVal.put("budgetItemSeqId",budgetitemattribute.getBudgetItemSeqId());
}

		if(budgetitemattribute.getAttrName() != null ){
			returnVal.put("attrName",budgetitemattribute.getAttrName());
}

		if(budgetitemattribute.getAttrValue() != null ){
			returnVal.put("attrValue",budgetitemattribute.getAttrValue());
}

		if(budgetitemattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",budgetitemattribute.getAttrDescription());
}

		return returnVal;
}


	public static BudgetItemAttribute map(Map<String, Object> fields) {

		BudgetItemAttribute returnVal = new BudgetItemAttribute();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
			returnVal.setAttrValue((long) fields.get("attrValue"));
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static BudgetItemAttribute mapstrstr(Map<String, String> fields) throws Exception {

		BudgetItemAttribute returnVal = new BudgetItemAttribute();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("budgetItemSeqId") != null) {
			returnVal.setBudgetItemSeqId((String) fields.get("budgetItemSeqId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("attrValue") != null) {
String buf;
buf = fields.get("attrValue");
long ibuf = Long.parseLong(buf);
			returnVal.setAttrValue(ibuf);
}

		if(fields.get("attrDescription") != null) {
			returnVal.setAttrDescription((String) fields.get("attrDescription"));
}


		return returnVal;
 } 
	public static BudgetItemAttribute map(GenericValue val) {

BudgetItemAttribute returnVal = new BudgetItemAttribute();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setBudgetItemSeqId(val.getString("budgetItemSeqId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static BudgetItemAttribute map(HttpServletRequest request) throws Exception {

		BudgetItemAttribute returnVal = new BudgetItemAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("budgetItemSeqId"))  {
returnVal.setBudgetItemSeqId(request.getParameter("budgetItemSeqId"));
}
		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("attrValue"))  {
String buf = request.getParameter("attrValue");
Long ibuf = Long.parseLong(buf);
returnVal.setAttrValue(ibuf);
}
		if(paramMap.containsKey("attrDescription"))  {
returnVal.setAttrDescription(request.getParameter("attrDescription"));
}
return returnVal;

}
}
