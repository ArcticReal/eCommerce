package com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.model.BudgetReviewResultType;

public class BudgetReviewResultTypeMapper  {


	public static Map<String, Object> map(BudgetReviewResultType budgetreviewresulttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetreviewresulttype.getBudgetReviewResultTypeId() != null ){
			returnVal.put("budgetReviewResultTypeId",budgetreviewresulttype.getBudgetReviewResultTypeId());
}

		if(budgetreviewresulttype.getDescription() != null ){
			returnVal.put("description",budgetreviewresulttype.getDescription());
}

		if(budgetreviewresulttype.getComments() != null ){
			returnVal.put("comments",budgetreviewresulttype.getComments());
}

		return returnVal;
}


	public static BudgetReviewResultType map(Map<String, Object> fields) {

		BudgetReviewResultType returnVal = new BudgetReviewResultType();

		if(fields.get("budgetReviewResultTypeId") != null) {
			returnVal.setBudgetReviewResultTypeId((String) fields.get("budgetReviewResultTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static BudgetReviewResultType mapstrstr(Map<String, String> fields) throws Exception {

		BudgetReviewResultType returnVal = new BudgetReviewResultType();

		if(fields.get("budgetReviewResultTypeId") != null) {
			returnVal.setBudgetReviewResultTypeId((String) fields.get("budgetReviewResultTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static BudgetReviewResultType map(GenericValue val) {

BudgetReviewResultType returnVal = new BudgetReviewResultType();
		returnVal.setBudgetReviewResultTypeId(val.getString("budgetReviewResultTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static BudgetReviewResultType map(HttpServletRequest request) throws Exception {

		BudgetReviewResultType returnVal = new BudgetReviewResultType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetReviewResultTypeId")) {
returnVal.setBudgetReviewResultTypeId(request.getParameter("budgetReviewResultTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
