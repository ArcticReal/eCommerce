package com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortStatus.model.WorkEffortStatus;

public class WorkEffortStatusMapper  {


	public static Map<String, Object> map(WorkEffortStatus workeffortstatus) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortstatus.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortstatus.getWorkEffortId());
}

		if(workeffortstatus.getStatusId() != null ){
			returnVal.put("statusId",workeffortstatus.getStatusId());
}

		if(workeffortstatus.getStatusDatetime() != null ){
			returnVal.put("statusDatetime",workeffortstatus.getStatusDatetime());
}

		if(workeffortstatus.getSetByUserLogin() != null ){
			returnVal.put("setByUserLogin",workeffortstatus.getSetByUserLogin());
}

		if(workeffortstatus.getReason() != null ){
			returnVal.put("reason",workeffortstatus.getReason());
}

		return returnVal;
}


	public static WorkEffortStatus map(Map<String, Object> fields) {

		WorkEffortStatus returnVal = new WorkEffortStatus();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDatetime") != null) {
			returnVal.setStatusDatetime((Timestamp) fields.get("statusDatetime"));
}

		if(fields.get("setByUserLogin") != null) {
			returnVal.setSetByUserLogin((String) fields.get("setByUserLogin"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}


		return returnVal;
 } 
	public static WorkEffortStatus mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortStatus returnVal = new WorkEffortStatus();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("statusDatetime") != null) {
String buf = fields.get("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setStatusDatetime(ibuf);
}

		if(fields.get("setByUserLogin") != null) {
			returnVal.setSetByUserLogin((String) fields.get("setByUserLogin"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}


		return returnVal;
 } 
	public static WorkEffortStatus map(GenericValue val) {

WorkEffortStatus returnVal = new WorkEffortStatus();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setStatusDatetime(val.getTimestamp("statusDatetime"));
		returnVal.setSetByUserLogin(val.getString("setByUserLogin"));
		returnVal.setReason(val.getString("reason"));


return returnVal;

}

public static WorkEffortStatus map(HttpServletRequest request) throws Exception {

		WorkEffortStatus returnVal = new WorkEffortStatus();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("statusDatetime"))  {
String buf = request.getParameter("statusDatetime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setStatusDatetime(ibuf);
}
		if(paramMap.containsKey("setByUserLogin"))  {
returnVal.setSetByUserLogin(request.getParameter("setByUserLogin"));
}
		if(paramMap.containsKey("reason"))  {
returnVal.setReason(request.getParameter("reason"));
}
return returnVal;

}
}
