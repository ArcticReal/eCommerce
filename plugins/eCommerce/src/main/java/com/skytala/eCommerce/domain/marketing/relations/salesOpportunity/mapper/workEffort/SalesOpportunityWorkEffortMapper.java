package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.workEffort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.workEffort.SalesOpportunityWorkEffort;

public class SalesOpportunityWorkEffortMapper  {


	public static Map<String, Object> map(SalesOpportunityWorkEffort salesopportunityworkeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(salesopportunityworkeffort.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",salesopportunityworkeffort.getSalesOpportunityId());
}

		if(salesopportunityworkeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",salesopportunityworkeffort.getWorkEffortId());
}

		return returnVal;
}


	public static SalesOpportunityWorkEffort map(Map<String, Object> fields) {

		SalesOpportunityWorkEffort returnVal = new SalesOpportunityWorkEffort();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static SalesOpportunityWorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		SalesOpportunityWorkEffort returnVal = new SalesOpportunityWorkEffort();

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}


		return returnVal;
 } 
	public static SalesOpportunityWorkEffort map(GenericValue val) {

SalesOpportunityWorkEffort returnVal = new SalesOpportunityWorkEffort();
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));


return returnVal;

}

public static SalesOpportunityWorkEffort map(HttpServletRequest request) throws Exception {

		SalesOpportunityWorkEffort returnVal = new SalesOpportunityWorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("salesOpportunityId")) {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
return returnVal;

}
}
