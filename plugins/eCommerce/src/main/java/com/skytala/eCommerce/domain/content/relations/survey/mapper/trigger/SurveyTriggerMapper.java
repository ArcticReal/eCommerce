package com.skytala.eCommerce.domain.content.relations.survey.mapper.trigger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.survey.model.trigger.SurveyTrigger;

public class SurveyTriggerMapper  {


	public static Map<String, Object> map(SurveyTrigger surveytrigger) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(surveytrigger.getSurveyId() != null ){
			returnVal.put("surveyId",surveytrigger.getSurveyId());
}

		if(surveytrigger.getSurveyApplTypeId() != null ){
			returnVal.put("surveyApplTypeId",surveytrigger.getSurveyApplTypeId());
}

		if(surveytrigger.getFromDate() != null ){
			returnVal.put("fromDate",surveytrigger.getFromDate());
}

		if(surveytrigger.getThruDate() != null ){
			returnVal.put("thruDate",surveytrigger.getThruDate());
}

		return returnVal;
}


	public static SurveyTrigger map(Map<String, Object> fields) {

		SurveyTrigger returnVal = new SurveyTrigger();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyApplTypeId") != null) {
			returnVal.setSurveyApplTypeId((String) fields.get("surveyApplTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static SurveyTrigger mapstrstr(Map<String, String> fields) throws Exception {

		SurveyTrigger returnVal = new SurveyTrigger();

		if(fields.get("surveyId") != null) {
			returnVal.setSurveyId((String) fields.get("surveyId"));
}

		if(fields.get("surveyApplTypeId") != null) {
			returnVal.setSurveyApplTypeId((String) fields.get("surveyApplTypeId"));
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
	public static SurveyTrigger map(GenericValue val) {

SurveyTrigger returnVal = new SurveyTrigger();
		returnVal.setSurveyId(val.getString("surveyId"));
		returnVal.setSurveyApplTypeId(val.getString("surveyApplTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static SurveyTrigger map(HttpServletRequest request) throws Exception {

		SurveyTrigger returnVal = new SurveyTrigger();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("surveyId")) {
returnVal.setSurveyId(request.getParameter("surveyId"));
}

		if(paramMap.containsKey("surveyApplTypeId"))  {
returnVal.setSurveyApplTypeId(request.getParameter("surveyApplTypeId"));
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
