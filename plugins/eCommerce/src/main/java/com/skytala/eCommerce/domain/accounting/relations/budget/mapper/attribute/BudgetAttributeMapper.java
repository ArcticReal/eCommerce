package com.skytala.eCommerce.domain.accounting.relations.budget.mapper.attribute;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;

public class BudgetAttributeMapper  {


	public static Map<String, Object> map(BudgetAttribute budgetattribute) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetattribute.getBudgetId() != null ){
			returnVal.put("budgetId",budgetattribute.getBudgetId());
}

		if(budgetattribute.getAttrName() != null ){
			returnVal.put("attrName",budgetattribute.getAttrName());
}

		if(budgetattribute.getAttrValue() != null ){
			returnVal.put("attrValue",budgetattribute.getAttrValue());
}

		if(budgetattribute.getAttrDescription() != null ){
			returnVal.put("attrDescription",budgetattribute.getAttrDescription());
}

		return returnVal;
}


	public static BudgetAttribute map(Map<String, Object> fields) {

		BudgetAttribute returnVal = new BudgetAttribute();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
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
	public static BudgetAttribute mapstrstr(Map<String, String> fields) throws Exception {

		BudgetAttribute returnVal = new BudgetAttribute();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
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
	public static BudgetAttribute map(GenericValue val) {

BudgetAttribute returnVal = new BudgetAttribute();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setAttrValue(val.getLong("attrValue"));
		returnVal.setAttrDescription(val.getString("attrDescription"));


return returnVal;

}

public static BudgetAttribute map(HttpServletRequest request) throws Exception {

		BudgetAttribute returnVal = new BudgetAttribute();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
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
