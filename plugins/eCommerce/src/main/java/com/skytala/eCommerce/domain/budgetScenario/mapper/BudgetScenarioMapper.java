package com.skytala.eCommerce.domain.budgetScenario.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.budgetScenario.model.BudgetScenario;

public class BudgetScenarioMapper  {


	public static Map<String, Object> map(BudgetScenario budgetscenario) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetscenario.getBudgetScenarioId() != null ){
			returnVal.put("budgetScenarioId",budgetscenario.getBudgetScenarioId());
}

		if(budgetscenario.getDescription() != null ){
			returnVal.put("description",budgetscenario.getDescription());
}

		return returnVal;
}


	public static BudgetScenario map(Map<String, Object> fields) {

		BudgetScenario returnVal = new BudgetScenario();

		if(fields.get("budgetScenarioId") != null) {
			returnVal.setBudgetScenarioId((String) fields.get("budgetScenarioId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetScenario mapstrstr(Map<String, String> fields) throws Exception {

		BudgetScenario returnVal = new BudgetScenario();

		if(fields.get("budgetScenarioId") != null) {
			returnVal.setBudgetScenarioId((String) fields.get("budgetScenarioId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static BudgetScenario map(GenericValue val) {

BudgetScenario returnVal = new BudgetScenario();
		returnVal.setBudgetScenarioId(val.getString("budgetScenarioId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static BudgetScenario map(HttpServletRequest request) throws Exception {

		BudgetScenario returnVal = new BudgetScenario();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetScenarioId")) {
returnVal.setBudgetScenarioId(request.getParameter("budgetScenarioId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
