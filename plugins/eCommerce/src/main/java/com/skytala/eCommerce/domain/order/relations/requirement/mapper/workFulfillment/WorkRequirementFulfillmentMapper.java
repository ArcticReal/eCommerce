package com.skytala.eCommerce.domain.order.relations.requirement.mapper.workFulfillment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.model.workFulfillment.WorkRequirementFulfillment;

public class WorkRequirementFulfillmentMapper  {


	public static Map<String, Object> map(WorkRequirementFulfillment workrequirementfulfillment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workrequirementfulfillment.getRequirementId() != null ){
			returnVal.put("requirementId",workrequirementfulfillment.getRequirementId());
}

		if(workrequirementfulfillment.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workrequirementfulfillment.getWorkEffortId());
}

		if(workrequirementfulfillment.getWorkReqFulfTypeId() != null ){
			returnVal.put("workReqFulfTypeId",workrequirementfulfillment.getWorkReqFulfTypeId());
}

		return returnVal;
}


	public static WorkRequirementFulfillment map(Map<String, Object> fields) {

		WorkRequirementFulfillment returnVal = new WorkRequirementFulfillment();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("workReqFulfTypeId") != null) {
			returnVal.setWorkReqFulfTypeId((String) fields.get("workReqFulfTypeId"));
}


		return returnVal;
 } 
	public static WorkRequirementFulfillment mapstrstr(Map<String, String> fields) throws Exception {

		WorkRequirementFulfillment returnVal = new WorkRequirementFulfillment();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("workReqFulfTypeId") != null) {
			returnVal.setWorkReqFulfTypeId((String) fields.get("workReqFulfTypeId"));
}


		return returnVal;
 } 
	public static WorkRequirementFulfillment map(GenericValue val) {

WorkRequirementFulfillment returnVal = new WorkRequirementFulfillment();
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setWorkReqFulfTypeId(val.getString("workReqFulfTypeId"));


return returnVal;

}

public static WorkRequirementFulfillment map(HttpServletRequest request) throws Exception {

		WorkRequirementFulfillment returnVal = new WorkRequirementFulfillment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementId")) {
returnVal.setRequirementId(request.getParameter("requirementId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("workReqFulfTypeId"))  {
returnVal.setWorkReqFulfTypeId(request.getParameter("workReqFulfTypeId"));
}
return returnVal;

}
}
