package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;

public class ApplicationSandboxMapper  {


	public static Map<String, Object> map(ApplicationSandbox applicationsandbox) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(applicationsandbox.getApplicationId() != null ){
			returnVal.put("applicationId",applicationsandbox.getApplicationId());
}

		if(applicationsandbox.getWorkEffortId() != null ){
			returnVal.put("workEffortId",applicationsandbox.getWorkEffortId());
}

		if(applicationsandbox.getPartyId() != null ){
			returnVal.put("partyId",applicationsandbox.getPartyId());
}

		if(applicationsandbox.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",applicationsandbox.getRoleTypeId());
}

		if(applicationsandbox.getFromDate() != null ){
			returnVal.put("fromDate",applicationsandbox.getFromDate());
}

		if(applicationsandbox.getRuntimeDataId() != null ){
			returnVal.put("runtimeDataId",applicationsandbox.getRuntimeDataId());
}

		return returnVal;
}


	public static ApplicationSandbox map(Map<String, Object> fields) {

		ApplicationSandbox returnVal = new ApplicationSandbox();

		if(fields.get("applicationId") != null) {
			returnVal.setApplicationId((String) fields.get("applicationId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("runtimeDataId") != null) {
			returnVal.setRuntimeDataId((String) fields.get("runtimeDataId"));
}


		return returnVal;
 } 
	public static ApplicationSandbox mapstrstr(Map<String, String> fields) throws Exception {

		ApplicationSandbox returnVal = new ApplicationSandbox();

		if(fields.get("applicationId") != null) {
			returnVal.setApplicationId((String) fields.get("applicationId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("runtimeDataId") != null) {
			returnVal.setRuntimeDataId((String) fields.get("runtimeDataId"));
}


		return returnVal;
 } 
	public static ApplicationSandbox map(GenericValue val) {

ApplicationSandbox returnVal = new ApplicationSandbox();
		returnVal.setApplicationId(val.getString("applicationId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setRuntimeDataId(val.getString("runtimeDataId"));


return returnVal;

}

public static ApplicationSandbox map(HttpServletRequest request) throws Exception {

		ApplicationSandbox returnVal = new ApplicationSandbox();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("applicationId")) {
returnVal.setApplicationId(request.getParameter("applicationId"));
}

		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("runtimeDataId"))  {
returnVal.setRuntimeDataId(request.getParameter("runtimeDataId"));
}
return returnVal;

}
}
