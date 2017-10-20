package com.skytala.eCommerce.domain.accounting.relations.budget.mapper.revision;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.revision.BudgetRevision;

public class BudgetRevisionMapper  {


	public static Map<String, Object> map(BudgetRevision budgetrevision) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(budgetrevision.getBudgetId() != null ){
			returnVal.put("budgetId",budgetrevision.getBudgetId());
}

		if(budgetrevision.getRevisionSeqId() != null ){
			returnVal.put("revisionSeqId",budgetrevision.getRevisionSeqId());
}

		if(budgetrevision.getDateRevised() != null ){
			returnVal.put("dateRevised",budgetrevision.getDateRevised());
}

		return returnVal;
}


	public static BudgetRevision map(Map<String, Object> fields) {

		BudgetRevision returnVal = new BudgetRevision();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("revisionSeqId") != null) {
			returnVal.setRevisionSeqId((String) fields.get("revisionSeqId"));
}

		if(fields.get("dateRevised") != null) {
			returnVal.setDateRevised((Timestamp) fields.get("dateRevised"));
}


		return returnVal;
 } 
	public static BudgetRevision mapstrstr(Map<String, String> fields) throws Exception {

		BudgetRevision returnVal = new BudgetRevision();

		if(fields.get("budgetId") != null) {
			returnVal.setBudgetId((String) fields.get("budgetId"));
}

		if(fields.get("revisionSeqId") != null) {
			returnVal.setRevisionSeqId((String) fields.get("revisionSeqId"));
}

		if(fields.get("dateRevised") != null) {
String buf = fields.get("dateRevised");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateRevised(ibuf);
}


		return returnVal;
 } 
	public static BudgetRevision map(GenericValue val) {

BudgetRevision returnVal = new BudgetRevision();
		returnVal.setBudgetId(val.getString("budgetId"));
		returnVal.setRevisionSeqId(val.getString("revisionSeqId"));
		returnVal.setDateRevised(val.getTimestamp("dateRevised"));


return returnVal;

}

public static BudgetRevision map(HttpServletRequest request) throws Exception {

		BudgetRevision returnVal = new BudgetRevision();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("budgetId")) {
returnVal.setBudgetId(request.getParameter("budgetId"));
}

		if(paramMap.containsKey("revisionSeqId"))  {
returnVal.setRevisionSeqId(request.getParameter("revisionSeqId"));
}
		if(paramMap.containsKey("dateRevised"))  {
String buf = request.getParameter("dateRevised");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateRevised(ibuf);
}
return returnVal;

}
}
