package com.skytala.eCommerce.domain.accounting.relations.budgetItemType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemType.model.BudgetItemType;

public class BudgetItemTypeMapper  {


	public static Map<String, Object> map(BudgetItemType budgetitemtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetitemtype.getBudgetItemTypeId() != null ){
			returnVal.put("budgetItemTypeId",budgetitemtype.getBudgetItemTypeId());
}

		if(budgetitemtype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",budgetitemtype.getParentTypeId());
}

		if(budgetitemtype.getHasTable() != null ){
			returnVal.put("hasTable",budgetitemtype.getHasTable());
}

		if(budgetitemtype.getDescription() != null ){
			returnVal.put("description",budgetitemtype.getDescription());
}

		return returnVal;
}


	public static BudgetItemType map(Map<String, Object> fields) {

		BudgetItemType returnVal = new BudgetItemType();

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetItemType mapstrstr(Map<String, String> fields) throws Exception {

		BudgetItemType returnVal = new BudgetItemType();

		if(fields.get("budgetItemTypeId") != null) {
			returnVal.setBudgetItemTypeId((String) fields.get("budgetItemTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetItemType map(GenericValue val) {

BudgetItemType returnVal = new BudgetItemType();
		returnVal.setBudgetItemTypeId(val.getString("budgetItemTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static BudgetItemType map(HttpServletRequest request) throws Exception {

		BudgetItemType returnVal = new BudgetItemType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetItemTypeId")) {
returnVal.setBudgetItemTypeId(request.getParameter("budgetItemTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
