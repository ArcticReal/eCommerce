package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.surveyAppl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.surveyAppl.WorkEffortSurveyAppl;

public class WorkEffortSurveyApplMapper  {


	public static Map<String, Object> map(WorkEffortSurveyAppl workeffortsurveyappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortsurveyappl.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortsurveyappl.getWorkEffortId());
}

		if(workeffortsurveyappl.getSurveyId() != null ){
			returnVal.put("surveyId",workeffortsurveyappl.getSurveyId());
}

		if(workeffortsurveyappl.getFromDate() != null ){
			returnVal.put("fromDate",workeffortsurveyappl.getFromDate());
}

		if(workeffortsurveyappl.getThruDate() != null ){
			returnVal.put("thruDate",workeffortsurveyappl.getThruDate());
}

		return returnVal;
}


	public static WorkEffortSurveyAppl map(Map<String, Object> fields) {

		WorkEffortSurveyAppl returnVal = new WorkEffortSurveyAppl();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static WorkEffortSurveyAppl mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortSurveyAppl returnVal = new WorkEffortSurveyAppl();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
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
	public static WorkEffortSurveyAppl map(GenericValue val) {

WorkEffortSurveyAppl returnVal = new WorkEffortSurveyAppl();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static WorkEffortSurveyAppl map(HttpServletRequest request) throws Exception {

		WorkEffortSurveyAppl returnVal = new WorkEffortSurveyAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("surveyId"))  {
returnVal.setSurveyId(request.getParameter("surveyId"));
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
