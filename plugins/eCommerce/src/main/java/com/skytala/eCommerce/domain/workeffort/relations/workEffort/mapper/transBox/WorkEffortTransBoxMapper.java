package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.transBox;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.transBox.WorkEffortTransBox;

public class WorkEffortTransBoxMapper  {


	public static Map<String, Object> map(WorkEffortTransBox workefforttransbox) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workefforttransbox.getProcessWorkEffortId() != null ){
			returnVal.put("processWorkEffortId",workefforttransbox.getProcessWorkEffortId());
}

		if(workefforttransbox.getToActivityId() != null ){
			returnVal.put("toActivityId",workefforttransbox.getToActivityId());
}

		if(workefforttransbox.getTransitionId() != null ){
			returnVal.put("transitionId",workefforttransbox.getTransitionId());
}

		return returnVal;
}


	public static WorkEffortTransBox map(Map<String, Object> fields) {

		WorkEffortTransBox returnVal = new WorkEffortTransBox();

		if(fields.get("processWorkEffortId") != null) {
			returnVal.setProcessWorkEffortId((String) fields.get("processWorkEffortId"));
}

		if(fields.get("toActivityId") != null) {
			returnVal.setToActivityId((String) fields.get("toActivityId"));
}

		if(fields.get("transitionId") != null) {
			returnVal.setTransitionId((String) fields.get("transitionId"));
}


		return returnVal;
 } 
	public static WorkEffortTransBox mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortTransBox returnVal = new WorkEffortTransBox();

		if(fields.get("processWorkEffortId") != null) {
			returnVal.setProcessWorkEffortId((String) fields.get("processWorkEffortId"));
}

		if(fields.get("toActivityId") != null) {
			returnVal.setToActivityId((String) fields.get("toActivityId"));
}

		if(fields.get("transitionId") != null) {
			returnVal.setTransitionId((String) fields.get("transitionId"));
}


		return returnVal;
 } 
	public static WorkEffortTransBox map(GenericValue val) {

WorkEffortTransBox returnVal = new WorkEffortTransBox();
		returnVal.setProcessWorkEffortId(val.getString("processWorkEffortId"));
		returnVal.setToActivityId(val.getString("toActivityId"));
		returnVal.setTransitionId(val.getString("transitionId"));


return returnVal;

}

public static WorkEffortTransBox map(HttpServletRequest request) throws Exception {

		WorkEffortTransBox returnVal = new WorkEffortTransBox();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("processWorkEffortId")) {
returnVal.setProcessWorkEffortId(request.getParameter("processWorkEffortId"));
}

		if(paramMap.containsKey("toActivityId"))  {
returnVal.setToActivityId(request.getParameter("toActivityId"));
}
		if(paramMap.containsKey("transitionId"))  {
returnVal.setTransitionId(request.getParameter("transitionId"));
}
return returnVal;

}
}
