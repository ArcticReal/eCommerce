package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.content.WorkEffortContent;

public class WorkEffortContentMapper  {


	public static Map<String, Object> map(WorkEffortContent workeffortcontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortcontent.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortcontent.getWorkEffortId());
}

		if(workeffortcontent.getContentId() != null ){
			returnVal.put("contentId",workeffortcontent.getContentId());
}

		if(workeffortcontent.getWorkEffortContentTypeId() != null ){
			returnVal.put("workEffortContentTypeId",workeffortcontent.getWorkEffortContentTypeId());
}

		if(workeffortcontent.getFromDate() != null ){
			returnVal.put("fromDate",workeffortcontent.getFromDate());
}

		if(workeffortcontent.getThruDate() != null ){
			returnVal.put("thruDate",workeffortcontent.getThruDate());
}

		return returnVal;
}


	public static WorkEffortContent map(Map<String, Object> fields) {

		WorkEffortContent returnVal = new WorkEffortContent();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("workEffortContentTypeId") != null) {
			returnVal.setWorkEffortContentTypeId((String) fields.get("workEffortContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static WorkEffortContent mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortContent returnVal = new WorkEffortContent();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("workEffortContentTypeId") != null) {
			returnVal.setWorkEffortContentTypeId((String) fields.get("workEffortContentTypeId"));
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


		return returnVal;
 } 
	public static WorkEffortContent map(GenericValue val) {

WorkEffortContent returnVal = new WorkEffortContent();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setWorkEffortContentTypeId(val.getString("workEffortContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static WorkEffortContent map(HttpServletRequest request) throws Exception {

		WorkEffortContent returnVal = new WorkEffortContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("workEffortContentTypeId"))  {
returnVal.setWorkEffortContentTypeId(request.getParameter("workEffortContentTypeId"));
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
return returnVal;

}
}
