package com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortDeliverableProd.model.WorkEffortDeliverableProd;

public class WorkEffortDeliverableProdMapper  {


	public static Map<String, Object> map(WorkEffortDeliverableProd workeffortdeliverableprod) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortdeliverableprod.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortdeliverableprod.getWorkEffortId());
}

		if(workeffortdeliverableprod.getDeliverableId() != null ){
			returnVal.put("deliverableId",workeffortdeliverableprod.getDeliverableId());
}

		return returnVal;
}


	public static WorkEffortDeliverableProd map(Map<String, Object> fields) {

		WorkEffortDeliverableProd returnVal = new WorkEffortDeliverableProd();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("deliverableId") != null) {
			returnVal.setDeliverableId((String) fields.get("deliverableId"));
}


		return returnVal;
 } 
	public static WorkEffortDeliverableProd mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortDeliverableProd returnVal = new WorkEffortDeliverableProd();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("deliverableId") != null) {
			returnVal.setDeliverableId((String) fields.get("deliverableId"));
}


		return returnVal;
 } 
	public static WorkEffortDeliverableProd map(GenericValue val) {

WorkEffortDeliverableProd returnVal = new WorkEffortDeliverableProd();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setDeliverableId(val.getString("deliverableId"));


return returnVal;

}

public static WorkEffortDeliverableProd map(HttpServletRequest request) throws Exception {

		WorkEffortDeliverableProd returnVal = new WorkEffortDeliverableProd();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("deliverableId"))  {
returnVal.setDeliverableId(request.getParameter("deliverableId"));
}
return returnVal;

}
}
