package com.skytala.eCommerce.domain.accounting.relations.budgetType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetType.model.BudgetType;

public class BudgetTypeMapper  {


	public static Map<String, Object> map(BudgetType budgettype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgettype.getBudgetTypeId() != null ){
			returnVal.put("budgetTypeId",budgettype.getBudgetTypeId());
}

		if(budgettype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",budgettype.getParentTypeId());
}

		if(budgettype.getHasTable() != null ){
			returnVal.put("hasTable",budgettype.getHasTable());
}

		if(budgettype.getDescription() != null ){
			returnVal.put("description",budgettype.getDescription());
}

		return returnVal;
}


	public static BudgetType map(Map<String, Object> fields) {

		BudgetType returnVal = new BudgetType();

		if(fields.get("budgetTypeId") != null) {
			returnVal.setBudgetTypeId((String) fields.get("budgetTypeId"));
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
	public static BudgetType mapstrstr(Map<String, String> fields) throws Exception {

		BudgetType returnVal = new BudgetType();

		if(fields.get("budgetTypeId") != null) {
			returnVal.setBudgetTypeId((String) fields.get("budgetTypeId"));
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
	public static BudgetType map(GenericValue val) {

BudgetType returnVal = new BudgetType();
		returnVal.setBudgetTypeId(val.getString("budgetTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static BudgetType map(HttpServletRequest request) throws Exception {

		BudgetType returnVal = new BudgetType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetTypeId")) {
returnVal.setBudgetTypeId(request.getParameter("budgetTypeId"));
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
