package com.skytala.eCommerce.domain.order.relations.requirement.mapper.status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;

public class RequirementStatusMapper  {


	public static Map<String, Object> map(RequirementStatus requirementstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementstatus.getRequirementId() != null ){
			returnVal.put("requirementId",requirementstatus.getRequirementId());
}

		if(requirementstatus.getStatusId() != null ){
			returnVal.put("statusId",requirementstatus.getStatusId());
}

		if(requirementstatus.getStatusDate() != null ){
			returnVal.put("statusDate",requirementstatus.getStatusDate());
}

		if(requirementstatus.getChangeByUserLoginId() != null ){
			returnVal.put("changeByUserLoginId",requirementstatus.getChangeByUserLoginId());
}

		return returnVal;
}


	public static RequirementStatus map(Map<String, Object> fields) {

		RequirementStatus returnVal = new RequirementStatus();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDate") != null) {
			returnVal.setStatusDate((Timestamp) fields.get("statusDate"));
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static RequirementStatus mapstrstr(Map<String, String> fields) throws Exception {

		RequirementStatus returnVal = new RequirementStatus();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDate") != null) {
String buf = fields.get("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDate(ibuf);
}

		if(fields.get("changeByUserLoginId") != null) {
			returnVal.setChangeByUserLoginId((String) fields.get("changeByUserLoginId"));
}


		return returnVal;
 } 
	public static RequirementStatus map(GenericValue val) {

RequirementStatus returnVal = new RequirementStatus();
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusDate(val.getTimestamp("statusDate"));
		returnVal.setChangeByUserLoginId(val.getString("changeByUserLoginId"));


return returnVal;

}

public static RequirementStatus map(HttpServletRequest request) throws Exception {

		RequirementStatus returnVal = new RequirementStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementId")) {
returnVal.setRequirementId(request.getParameter("requirementId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusDate"))  {
String buf = request.getParameter("statusDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDate(ibuf);
}
		if(paramMap.containsKey("changeByUserLoginId"))  {
returnVal.setChangeByUserLoginId(request.getParameter("changeByUserLoginId"));
}
return returnVal;

}
}
