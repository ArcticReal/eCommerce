package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;

public class BudgetItemTypeAttrMapper  {


	public static Map<String, Object> map(BudgetItemTypeAttr budgetitemtypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetitemtypeattr.getBudgetItemTypeId() != null ){
			returnVal.put("budgetItemTypeId",budgetitemtypeattr.getBudgetItemTypeId());
}

		if(budgetitemtypeattr.getAttrName() != null ){
			returnVal.put("attrName",budgetitemtypeattr.getAttrName());
}

		if(budgetitemtypeattr.getDescription() != null ){
			returnVal.put("description",budgetitemtypeattr.getDescription());
}

		return returnVal;
}


	public static BudgetItemTypeAttr map(Map<String, Object> fields) {

		BudgetItemTypeAttr returnVal = new BudgetItemTypeAttr();

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetItemTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		BudgetItemTypeAttr returnVal = new BudgetItemTypeAttr();

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetItemTypeAttr map(GenericValue val) {

BudgetItemTypeAttr returnVal = new BudgetItemTypeAttr();
		returnVal.setBudgetItemTypeId(val.getString("budgetItemTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static BudgetItemTypeAttr map(HttpServletRequest request) throws Exception {

		BudgetItemTypeAttr returnVal = new BudgetItemTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetItemTypeId")) {
returnVal.setBudgetItemTypeId(request.getParameter("budgetItemTypeId"));
}

		if(paramMap.containsKey("attrName"))  {
returnVal.setAttrName(request.getParameter("attrName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
