package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.contactMech;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.contactMech.WorkEffortContactMech;

public class WorkEffortContactMechMapper  {


	public static Map<String, Object> map(WorkEffortContactMech workeffortcontactmech) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortcontactmech.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortcontactmech.getWorkEffortId());
}

		if(workeffortcontactmech.getContactMechId() != null ){
			returnVal.put("contactMechId",workeffortcontactmech.getContactMechId());
}

		if(workeffortcontactmech.getFromDate() != null ){
			returnVal.put("fromDate",workeffortcontactmech.getFromDate());
}

		if(workeffortcontactmech.getThruDate() != null ){
			returnVal.put("thruDate",workeffortcontactmech.getThruDate());
}

		if(workeffortcontactmech.getComments() != null ){
			returnVal.put("comments",workeffortcontactmech.getComments());
}

		return returnVal;
}


	public static WorkEffortContactMech map(Map<String, Object> fields) {

		WorkEffortContactMech returnVal = new WorkEffortContactMech();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static WorkEffortContactMech mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortContactMech returnVal = new WorkEffortContactMech();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("contactMechId") != null) {
			returnVal.setContactMechId((String) fields.get("contactMechId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static WorkEffortContactMech map(GenericValue val) {

WorkEffortContactMech returnVal = new WorkEffortContactMech();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setContactMechId(val.getString("contactMechId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static WorkEffortContactMech map(HttpServletRequest request) throws Exception {

		WorkEffortContactMech returnVal = new WorkEffortContactMech();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("contactMechId"))  {
returnVal.setContactMechId(request.getParameter("contactMechId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
