package com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.model.BudgetTypeAttr;

public class BudgetTypeAttrMapper  {


	public static Map<String, Object> map(BudgetTypeAttr budgettypeattr) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgettypeattr.getBudgetTypeId() != null ){
			returnVal.put("budgetTypeId",budgettypeattr.getBudgetTypeId());
}

		if(budgettypeattr.getAttrName() != null ){
			returnVal.put("attrName",budgettypeattr.getAttrName());
}

		if(budgettypeattr.getDescription() != null ){
			returnVal.put("description",budgettypeattr.getDescription());
}

		return returnVal;
}


	public static BudgetTypeAttr map(Map<String, Object> fields) {

		BudgetTypeAttr returnVal = new BudgetTypeAttr();

		if(fields.get("budgetTypeId") != null) {
			returnVal.setBudgetTypeId((String) fields.get("budgetTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetTypeAttr mapstrstr(Map<String, String> fields) throws Exception {

		BudgetTypeAttr returnVal = new BudgetTypeAttr();

		if(fields.get("budgetTypeId") != null) {
			returnVal.setBudgetTypeId((String) fields.get("budgetTypeId"));
}

		if(fields.get("attrName") != null) {
			returnVal.setAttrName((String) fields.get("attrName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetTypeAttr map(GenericValue val) {

BudgetTypeAttr returnVal = new BudgetTypeAttr();
		returnVal.setBudgetTypeId(val.getString("budgetTypeId"));
		returnVal.setAttrName(val.getString("attrName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static BudgetTypeAttr map(HttpServletRequest request) throws Exception {

		BudgetTypeAttr returnVal = new BudgetTypeAttr();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetTypeId")) {
returnVal.setBudgetTypeId(request.getParameter("budgetTypeId"));
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
